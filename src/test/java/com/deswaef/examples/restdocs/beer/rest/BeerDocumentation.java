package com.deswaef.examples.restdocs.beer.rest;

import com.deswaef.examples.restdocs.SpringRestdocsExampleApplication;
import com.deswaef.examples.restdocs.beer.service.BeerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.hateoas.MediaTypes;
import org.springframework.restdocs.RestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringRestdocsExampleApplication.class)
@WebAppConfiguration
public class BeerDocumentation {

    @Rule
    public final RestDocumentation restDocumentation = new RestDocumentation("build/generated-snippets");

    private RestDocumentationResultHandler document;

    @Autowired
    private BeerService beerService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

        this.document = document("{method-name}",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()));

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .alwaysDo(this.document)
                .build();
    }

    @Test
    public void beerListExample() throws Exception {
        this.mockMvc
                .perform(get("/beers"))
                .andExpect(status().isOk());

        this.mockMvc
                .perform(
                        get("/beers").contentType(MediaTypes.HAL_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void beerByIdExample() throws Exception {
        Map<String, Object> beer = new HashMap<>();
        beer.put("alcohol", 12.0);
        beer.put("name", "Quinten's Beer");
        String location = this.mockMvc
                .perform(post("/beers").contentType(MediaTypes.HAL_JSON).content(this.objectMapper.writeValueAsString(beer)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getHeader("Location");

        this.document.snippets(
                links(
                        linkWithRel("self").description("This <<resources-note,note>>")),
                responseFields(
                        fieldWithPath("internalId").description("The internal id of the beer"),
                        fieldWithPath("name").description("the name of the beer"),
                        fieldWithPath("alcohol").description("The amount of alcohol in the beer"),
                        fieldWithPath("_links").description("<<resources-note-links,Links>> to other resources")));

        this.mockMvc
                .perform(get(location))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(beer.get("name"))))
                .andExpect(jsonPath("alcohol", containsString(String.valueOf(beer.get("alcohol")))))
                .andExpect(jsonPath("_links.self.href", is(location)));
    }
}
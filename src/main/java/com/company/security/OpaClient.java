package com.company.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j @Component
public class OpaClient {

    private static final String URI = "http://localhost:8181/v1/data/authz/allow";

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RestTemplate restTemplate = new RestTemplate();

    public boolean allow(String action, Map<String, Object> resourceAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || action == null || resourceAttributes == null || resourceAttributes.isEmpty()) {
            log.error("obj is null!");
            return false;
        }

        String name = authentication.getName();

        List<String> authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority).toList();

        Map<String, Object> subjectAttributes = Map.of(
                "name", name,
                "authorities", authorities
        );


        Map<String, Object> input = Map.of(
                "subject", subjectAttributes,
                "resource", resourceAttributes,
                "action", action
        );

        ObjectNode requestNode = objectMapper.createObjectNode();
        requestNode.set("input", objectMapper.valueToTree(input));
        log.info("Authorization request:\n" + requestNode.toPrettyString());

        JsonNode responseNode = Objects.requireNonNull(restTemplate.postForObject(URI, requestNode, JsonNode.class));
        log.info("Authorization response:\n" + responseNode.toPrettyString());

        return responseNode.has("result") && responseNode.get("result").asBoolean();
    }
}

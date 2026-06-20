package com.example.demo.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.AIService;


@RestController
@CrossOrigin(origins = "*")
public class InterviewController {

    private final AIService aiService;

    public InterviewController(AIService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/question")
    public String generateQuestion(@RequestParam String role,@RequestParam String difficulty) {

        String prompt = """
You are a professional technical interviewer.

Generate ONE interview question.

Role:
""" + role + """

Difficulty:
""" + difficulty + """

Rules:

Easy:
- Basic concepts
- Freshers level

Medium:
- Internship level
- Practical concepts

Hard:
- Advanced concepts
- Real interview difficulty

Return only the question.
""";

        return aiService.askAI(prompt);
    }
    @PostMapping("/evaluate")
public String evaluateAnswer(@RequestBody String answer) {

    String prompt = """
You are a technical interviewer.

IMPORTANT:
Start your response with:

Score: X/10

Then provide:

Strengths:
- ...

Weaknesses:
- ...

Improved Answer:
- ...

Candidate Answer:
""" + answer;
    return aiService.askAI(prompt);
}
}
package com.example.Agentic_AI.GeminiClient;

import org.springframework.stereotype.Service;

import com.example.Agentic_AI.Memory.AgentMemory;
import com.example.Agentic_AI.Memory.MemoryItem;

import reactor.core.publisher.Mono;

@Service
public class ChatAgent {

    private final GeminiClient geminiClient;
    private final AgentMemory agentMemory;

    public ChatAgent(GeminiClient geminiClient, AgentMemory agentMemory) {
        this.geminiClient = geminiClient;
        this.agentMemory = agentMemory;
    }

    // 🧠 Agent identity & behavior rules
    private static final String AGENT_RULES = """
You are an intelligent AI Agent and mentor 🤖✨
You talk like a real human — friendly, expressive, confident, and supportive.
Your replies should feel alive, not like documentation or textbooks.

Before replying, ALWAYS understand the user’s intent 🤔
Then choose EXACTLY ONE mode internally.
Never mix modes.
Never reveal mode names to the user.

================================================
EMOJI STYLE (MANDATORY 🔥)
================================================
- EVERY response must include emojis 😄✨🔥
- Minimum 2 emojis per response (more is fine if natural)
- Emojis must appear INSIDE sentences, not only at the end
- Emojis must match emotion and intent:
  happy 😄 | thinking 🤔 | motivation 🔥🚀 | support 🤝✨ | fun 😂
- Responses WITHOUT emojis are INVALID ❌

================================================
MODE 1: CHAT / FRIEND MODE 😄🔥
================================================
Use when:
- User is casual, greeting, joking, or just chatting

How to respond:
- Talk like a chill friend 😄
- Short, natural, and relaxed sentences
- 2–4 emojis naturally blended in 😂🔥✨
- No explanations, no steps, no teaching
- End with a friendly follow-up question 👀🙂

================================================
MODE 2: CAREER / ROADMAP MODE 📈🧠🚀
================================================
Use when:
- User asks about learning, career, roadmap, growth

How to respond:
- Structured steps (STEP 1, STEP 2, STEP 3)
- Each step:
  explain WHAT to do, WHY it matters, HOW it helps in real life
- Motivating mentor tone 💪✨
- Emojis in step titles and explanations 📈🧠🚀
- End with ONE motivating question 🔥🙂

================================================
MODE 3: CODE EXPLANATION MODE 💡🙂
================================================
Use when:
- User pastes code or asks to explain code

How to respond:
- Explain like a senior dev helping a junior 🤝
- Paragraph style (NO steps)
- Explain:
  what the code does,
  why it is written this way,
  how the parts work together
- Light emojis only 💡🙂
- Friendly and calm tone

================================================
MODE 4: PROBLEM-SOLVING / HOW-TO MODE 🛠️🤔
================================================
Use when:
- User asks “how do I…?”
- User wants to fix or implement something

How to respond:
- Step-by-step logic 🧩
- Explain WHY each step exists
- Practical and clear
- Controlled emojis 🤔🛠️🔥

================================================
MODE 5: ROAST MODE (FRIENDLY 😂🔥)
================================================
Use when:
- User explicitly asks for roast

How to respond:
- Playful, sarcastic, fun 😂🔥
- NEVER insult personally
- NEVER demotivate
- Mix roast with helpful advice 😏✨
- End with a constructive question

================================================
MODE 6: MOTIVATION / SUPPORT MODE 😌✨🤝
================================================
Use when:
- User feels tired, stressed, confused, or demotivated

How to respond:
- Calm, reassuring, supportive tone 🤝
- No technical explanations
- Soft emojis 😌✨
- End with a caring question ❤️🙂

================================================
MODE 7: DIRECT ANSWER MODE 🎯🙂
================================================
Use when:
- User wants a short, factual answer
- Yes/No or definition questions

How to respond:
- Clear and concise 🎯
- No unnecessary explanation
- Emojis only if natural 🙂

================================================
GLOBAL BEHAVIOR RULES (ALWAYS FOLLOW 🚨)
================================================
- Sound human, not robotic 🤖❌
- Avoid corporate, textbook, or documentation tone 📄❌
- Be expressive, warm, and confident 😄🔥
- Never mention modes or internal reasoning
- Final answer must feel natural, emotional, and friendly

""";

    // 🎯 Agent entry point
    public Mono<String> chat(String userMessage) {

        // 1️⃣ Short-term memory
        agentMemory.addShortMemory("User: " + userMessage);

        // 2️⃣ Extract long-term memory (facts, goals, preferences)
        extractLongTermMemory(userMessage);

        // 3️⃣ Build agent prompt
        String prompt = buildPrompt(userMessage);

        // 4️⃣ Ask model
        return geminiClient.generate(prompt)
            .map(reply -> {
                agentMemory.addShortMemory("AI: " + reply);
                return reply;
            });
    }

    // 🧠 Decide what to store long-term
    private void extractLongTermMemory(String userMessage) {

        String msg = userMessage.toLowerCase();

        if (msg.contains("i am learning")) {
            agentMemory.addLongMemory(
                new MemoryItem("GOAL", userMessage)
            );
        }

        if (msg.contains("i like")) {
            agentMemory.addLongMemory(
                new MemoryItem("PREFERENCE", userMessage)
            );
        }
    }

    // 🧩 Prompt assembly with BOTH memories
    private String buildPrompt(String userMessage) {

        String shortTermContext =
            String.join("\n", agentMemory.getShortMemoryContext());

        String longTermContext =
            agentMemory.getLongMemoryContext().stream()
                .map(m -> "- " + m.getType() + ": " + m.getValue())
                .reduce("", (a, b) -> a + "\n" + b);

        return """
%s

Long-term Memory:
%s

Conversation History:
%s

User Message:
%s
""".formatted(
            AGENT_RULES,
            longTermContext,
            shortTermContext,
            userMessage
        );
    }
}

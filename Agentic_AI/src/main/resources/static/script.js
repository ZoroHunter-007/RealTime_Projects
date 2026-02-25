const chatBox = document.getElementById("chat-box");
const userInput = document.getElementById("userInput");
const sendBtn = document.getElementById("sendBtn");

let isRequestInProgress = false;

/* Add message bubble */
function addMessage(text, sender, extraClass = "") {
    const div = document.createElement("div");
    div.className = `message ${sender} ${extraClass}`;
    div.textContent = text;
    chatBox.appendChild(div);
    chatBox.scrollTop = chatBox.scrollHeight;
    return div;
}

/* Typing effect */
function typeEffect(text) {
    const msgDiv = addMessage("", "ai");
    let i = 0;

    const typing = setInterval(() => {
        if (i < text.length) {
            msgDiv.textContent += text.charAt(i);
            i++;
            chatBox.scrollTop = chatBox.scrollHeight;
        } else {
            clearInterval(typing);

            // 🔥 RE-ENABLE INPUT HERE
            isRequestInProgress = false;
            userInput.disabled = false;
            sendBtn.disabled = false;
            userInput.focus();
        }
    }, 25);
}


/* Send message */
function sendMessage() {
    const message = userInput.value.trim();

    // 🚫 Block multiple requests
    if (isRequestInProgress) {
        addMessage("⏳ Please wait, AI is still responding...", "ai");
        return;
    }

    if (!message) return;

    isRequestInProgress = true;
    userInput.disabled = true;
    sendBtn.disabled = true;

    addMessage(message, "user");
    userInput.value = "";

    // Thinking message
    const thinkingMsg = addMessage("🤖 Agent is thinking...", "ai", "thinking");

    fetch("/api/ai", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
		credentials:"include",
        body: JSON.stringify({ message })
    })
    .then(res => {
        if (res.status === 429) {
            throw new Error("RATE_LIMIT");
        }
        return res.text();
    })
    .then(reply => {
        thinkingMsg.remove();
        typeEffect(reply);
    })
	.catch(err => {
	    thinkingMsg.remove();

	    if (err.message === "RATE_LIMIT") {
	        addMessage(
	            "⏸ AI is taking a short break\n\n" +
	            "You’ve sent requests too quickly.\n" +
	            "Please wait 1–2 minutes and try again.\n\n" +
	            "(This is a temporary limit, not an error)",
	            "ai",
	            "system"
	        );
	    } else {
	        addMessage(
	            "⚠️ Something went wrong.\nPlease try again later.",
	            "ai",
	            "system"
	        );
	    }
	});

}

/* Enter key support */
userInput.addEventListener("keydown", function (e) {
    if (e.key === "Enter" && !e.shiftKey) {
        e.preventDefault();
        sendMessage();
    }
});

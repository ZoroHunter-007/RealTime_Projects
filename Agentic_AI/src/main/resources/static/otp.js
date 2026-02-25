async function verifyOtp() {
  const otp = document.getElementById("otp").value;
  const email = sessionStorage.getItem("email");

  const res = await fetch("/api/auth/verify-otp", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ email, otp })
  });

  if (res.ok) {
    window.location.href = "/index.html";
  } else {
    document.getElementById("error").innerText =
      "Invalid or expired OTP";
  }
}

async function resendOtp() {
  const email = sessionStorage.getItem("email");

  await fetch(`/api/auth/verify-email?email=${email}`);
  alert("New OTP sent to your email");
}

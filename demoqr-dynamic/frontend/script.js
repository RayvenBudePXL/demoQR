const QR_URL = "http://localhost:8080/qr";
const REFRESH_MS = 20000;

let refreshTimer = null;
let countdownTimer = null;
let expiresAt = 0;

function getStatusElement() {
    let status = document.getElementById("qrStatus");

    if (!status) {
        status = document.createElement("p");
        status.id = "qrStatus";
        document.querySelector("main")?.appendChild(status);
    }

    return status;
}

function setStatus(message) {
    const status = getStatusElement();

    if (status) {
        status.textContent = message;
    }
}

function setCountdown(secondsLeft) {
    const countdown = document.getElementById("countdown");

    if (countdown) {
        countdown.textContent = `${secondsLeft}s`;
    }
}

function stopCountdown() {
    clearInterval(countdownTimer);
    countdownTimer = null;
}

function startCountdown() {
    stopCountdown();
    setCountdown(Math.ceil(REFRESH_MS / 1000));

    countdownTimer = setInterval(() => {
        const remainingMs = expiresAt - Date.now();
        const remainingSeconds = Math.max(0, Math.ceil(remainingMs / 1000));

        setCountdown(remainingSeconds);

        if (remainingSeconds === 0) {
            stopCountdown();
        }
    }, 250);
}

function updateQrImage() {
    const img = document.getElementById("qrImage");

    if (!img) {
        return;
    }

    expiresAt = Date.now() + REFRESH_MS;
    img.src = `${QR_URL}?t=${Date.now()}`;
    img.alt = "Dynamische QR-code";
    setStatus("QR-code is actief en wordt elke 20 seconden vernieuwd.");
    startCountdown();
}

function generateQr() {
    clearInterval(refreshTimer);
    updateQrImage();
    refreshTimer = setInterval(updateQrImage, REFRESH_MS);
}

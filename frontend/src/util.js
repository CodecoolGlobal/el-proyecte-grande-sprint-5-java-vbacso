export function extractEmailFromToken(token) {
    if (!token) {
        return
    }
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace('-', '+').replace('_', '/');
    return JSON.parse(window.atob(base64)).sub;
}

export function getAuthenticationToken() {
    return localStorage.getItem("token");
}
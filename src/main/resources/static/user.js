document.addEventListener('DOMContentLoaded', async function () {
    await showUserNameOnNavbar()
    await fillTableAboutUser();
});

async function dataAboutCurrentUser() {
    const response = await fetch("/api/user")
    return await response.json();
}
async function fillTableAboutUser(){
    const currentUserTable1 = document.getElementById("currentUserTable");
    const currentUser = await dataAboutCurrentUser();

    let currentUserTableHTML = "";
    currentUserTableHTML +=
        `<tr>
            <td>${currentUser.id}</td>
            <td>${currentUser.username}</td>
            <td>${currentUser.lastName}</td>
            <td>${currentUser.year}</td>
            <td>${currentUser.email}</td>
            <td>${currentUser.roles.map(role => role.shortName).join(' ')}</td>
        </tr>`
    currentUserTable1.innerHTML = currentUserTableHTML;
}

async function showUserNameOnNavbar() {
    const currentUserNameNavbar = document.getElementById("currentUserEmailNavbar")
    const currentUser = await dataAboutCurrentUser();
    currentUserNameNavbar.innerHTML =
        `<strong>${currentUser.username}</strong>
                 with roles: 
                 ${currentUser.roles.map(role => role.shortName).join(' ')}`;
}
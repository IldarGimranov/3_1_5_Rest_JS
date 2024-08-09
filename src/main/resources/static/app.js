document.addEventListener('DOMContentLoaded', async function () {
    await showUserNameOnNavbar()
    await fillTableOfAllUsers();
    await fillTableAboutCurrentUser();
    await addNewUserForm();
    await DeleteModalHandler();
    await EditModalHandler();
});

async function showUserNameOnNavbar() {
    const currentUserEmailNavbar = document.getElementById("currentUserEmailNavbar")
    const currentUser = await dataAboutCurrentUser();
    currentUserEmailNavbar.innerHTML =
        `<strong>${currentUser.username}</strong>
                 with roles: 
                 ${currentUser.authorities.map(role => role.name.substring(5).concat(" ")).toString().replaceAll(",", "")}`;
}
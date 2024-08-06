async function createNewUser(user) {
    await fetch("/api/admin",
        {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(user)})
}

async function addNewUserForm() {
    const newUserForm = document.getElementById("newUser");

    newUserForm.addEventListener('submit', async function (event) {
        event.preventDefault();

        const username = newUserForm.querySelector("#userName").value.trim();
        const lastName = newUserForm.querySelector("#lastName").value.trim();
        const year = newUserForm.querySelector("#year").value.trim();
        const email = newUserForm.querySelector("#email").value.trim();
        const password = newUserForm.querySelector("#password").value.trim();

        const rolesSelected = document.getElementById("authorities");

        let roles = [];
        for (let option of rolesSelected.selectedOptions) {
            if (option.value === ROLE_USER.name) {
                roles.push(ROLE_USER);
            } else if (option.value === ROLE_ADMIN.name) {
                roles.push(ROLE_ADMIN, ROLE_USER);
            }
        }

        const newUserData = {
            username: username,
            lastName: lastName,
            year: year,
            email: email,
            password: password,
            roles: roles
        };

        await createNewUser(newUserData);
        newUserForm.reset();

        document.querySelector('a#show-users-table').click();
        await fillTableOfAllUsers();
    });
}
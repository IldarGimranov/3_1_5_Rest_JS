async function getUserDataById(userId) {
    const response = await fetch(`/api/users/${userId}`);
    return await response.json();
}
async function fillModal(modal) {

    modal.addEventListener("show.bs.modal", async function(event) {

        const userId = event.relatedTarget.dataset.userId;
        const user = await getUserDataById(userId);

        const modalBody = modal.querySelector(".modal-body");

        const idInput = modalBody.querySelector("input[data-user-id='id']");
        const usernameInput = modalBody.querySelector("input[data-user-id='username']");
        const lastNameInput = modalBody.querySelector("input[data-user-id='lastName']");
        const passwordInput = modalBody.querySelector("input[data-user-id='password']");
        const emailInput = modalBody.querySelector("input[data-user-id='email']");
        const yearInput = modalBody.querySelector("input[data-user-id='year']");
        if (passwordInput !== null) {
            passwordInput.value = user.password;
        }

        idInput.value = user.id;
        usernameInput.value = user.username;
        lastNameInput.value = user.lastName;
        yearInput.value = user.year;
        emailInput.value = user.email;


        let rolesSelect = HTMLSelectElement;

        let rolesSelectDelete = modalBody.querySelector("select[data-user-id='rolesDelete']");
        let rolesSelectEdit = modalBody.querySelector("select[data-user-id='rolesEdit']");
        let userRolesHTML = "";

        if (rolesSelectDelete !== null) {
            rolesSelect = rolesSelectDelete;
            for (let i = 0; i < user.authorities.length; i++) {
                userRolesHTML +=
                    `<option value="${user.authorities[i].name}">${user.authorities[i]
                        .substring(5).concat(" ").toString().replaceAll(",", "")}</option>`;
            }
        } else if (rolesSelectEdit !== null) {
            rolesSelect = rolesSelectEdit;
            userRolesHTML +=
                `<option value="ROLE_USER">USER</option>
                 <option value="ROLE_ADMIN">ADMIN</option>`
        }

        rolesSelect.innerHTML = userRolesHTML;
    })
}
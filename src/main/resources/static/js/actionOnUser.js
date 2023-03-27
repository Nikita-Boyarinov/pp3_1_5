
async function fillFieldsRoleForForm(form) {
    await fetch(getRolesUrl)
        .then(async rolesFromBD => {
            let allOptions = formNewUser.elements.roles.options;
            if (allOptions.length > 0) {
                $('#roles2').children().remove().end()
            }
            let rolesJSON = await rolesFromBD.json();
            form.elements.roles.setAttribute("size", Object.keys(rolesJSON).length);
            form.elements.roles.setAttribute("style", "text-transform:uppercase");
            for (let role of rolesJSON) {
                form.elements.roles.append(new Option(role.name, role.id));
            }
        }).catch(err => alert(err));

}

async function getUserByIDFromBD(id, form, modal) {

    await fetch(userUrl + `/${id}`).then(async userFromBD => {
        let userJSON = await userFromBD.json();
        form.elements.id.value = `${userJSON.id}`;
        form.elements.name.value = `${userJSON.name}`;
        form.elements.lastName.value = `${userJSON.lastName}`;
        form.elements.age.value = `${userJSON.age}`;
        form.elements.email.value = `${userJSON.email}`;
        form.elements.password.value = `${userJSON.password}`;
        fillFieldsRoleForForm(form).then(() => {
            userJSON.roles.forEach(role => {
                for (let i = 0; i < form.elements.roles.options.length; i++) {
                    if (role.name === form.elements.roles.options[i].text) {
                        form.elements.roles.options[i].selected = true;
                    }
                }
            })
        })
        modal.show();
    }).catch(err => alert(err));
}


async function actionOnUser(form, modal) {
    let rol = [];
    let dataUser = {};
    let url = '';
    Array.from(form.roles.options).filter(option => option.selected).map(option => {
        let objRole = {};
        objRole["id"] = option.value;
        rol.push(objRole);
    })
    if (form.getAttribute("method") === 'PUT') {
        url = userUrl + `/${form.id.value}`;
        dataUser = {
            id: form.id.value,
            name: form.name.value,
            lastName: form.lastName.value,
            age: form.age.value,
            email: form.email.value,
            password: form.password.value,
            roles: rol
        }
    }
    if (form.getAttribute("method") === 'POST') {
        url = userUrl;
        dataUser = {
            name: form.name.value,
            lastName: form.lastName.value,
            age: form.age.value,
            email: form.email.value,
            password: form.password.value,
            roles: rol
        }
    }
    if (form.getAttribute("method") === 'DELETE') {
        url = userUrl + `/${form.id.value}`;
    }


    let options = {
        method: form.getAttribute("method"),
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(dataUser)
    };
    await fetch(url, options)
        .then(response => {
            console.log(response.status);
            if (response.ok) {
                console.log("метод POST отправлен")
                if (form.getAttribute("method") === 'POST') {
                    console.log("метод POST отправлен")
                    document.getElementById('btnNavUsersTable').click();
                    console.log("кнопка таблицы со всеми юзерами нажата")
                    getMainTable();
                } else {
                    modal.hide();
                    getMainTable();
                }

            }
        })

}

async function getUserByIDFromBD(id, form, modal) {

    let userFromBD = await fetch(userUrl + `/${id}`);
    let rolesFromBD = await fetch(getRolesUrl);
    if (userFromBD.ok) {
        let userJSON = await userFromBD.json();
        let rolesJSON = await rolesFromBD.json();
        form.elements.id.value = `${userJSON.id}`;
        form.elements.name.value = `${userJSON.name}`;
        form.elements.lastName.value = `${userJSON.lastName}`;
        form.elements.age.value = `${userJSON.age}`;
        form.elements.email.value = `${userJSON.email}`;
        form.elements.password.value = `${userJSON.password}`;
        form.elements.roles.setAttribute("size", Object.keys(rolesJSON).length);
        form.elements.roles.setAttribute("style", "text-transform:uppercase");
        for (let role of rolesJSON) {
            form.elements.roles.append(new Option(role.name, role.id));
        }
        userJSON.roles.forEach(role => {
            for (let i = 0; i < form.elements.roles.options.length; i++) {
                if (role.name === form.elements.roles.options[i].text) {
                    form.elements.roles.options[i].selected = true;
                }
            }
        })
        modal.show();


    } else {
        alert(`Error HTTP:  ${userFromBD.status}`)
    }
}


async function actionOnUser(form, modal) {
    let rol =[];
    let dataUser = {};
    Array.from(form.roles.options).filter(option => option.selected).map(option => {
        let objRole = {};
        objRole["id"]=option.value;
        rol.push(objRole);
    })
    if (form.getAttribute("method") === 'PUT'){
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


    let options = {
        method: form.getAttribute("method"),
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(dataUser)
    };
    await fetch(userUrl + `/${form.id.value}`, options)
        .then(response => {
            if (response.ok){
                modal.hide();
                getMainTable();
            }
        })

}
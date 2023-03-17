


async function getUsersFromBD() {
    let listUsers = await fetch(adminTableUrl);
    if (listUsers.ok) {
        return await listUsers.json();
    } else {
        alert(`Error HTTP:   ${listUsers.status}`);
    }
}


function fillDataForMainTable(users) {
    let mainTable = '';

    for (let user of users) {
        let roles = '';
        user.roles.forEach(role => {
            roles += ' ';
            roles += role.name;
        })
        mainTable +=
            `<tr>
                <td>${user.id}</td>
                <td >${user.name}</td>
                <td>${user.lastName}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td style="text-transform:uppercase">${roles}</td>
                <td>
                    <input class="btn btn-info btn-block text-white" type="submit"
                        value="Edit" 
                        onclick="getUserByIDFromBD(${user.id}, formEdit, modalEditBS)">
                       
                </td>

                <td>
                    <input class="btn btn-danger  btn-block" type="submit"
                        value="Delete" onclick="getUserByIDFromBD(${user.id}, formDelete, modalDeleteBS)">
                      
                </td>
            </tr>`
        document.getElementById("mainTable").innerHTML = mainTable;
    }

}
async function getMainTable(){
   await getUsersFromBD().then(res => fillDataForMainTable(res))
}

fillNavBar();
getMainTable();


modalEdit.addEventListener('hidden.bs.modal', () => {
    let allOptions = formEdit.elements.roles.options;
    if (allOptions.length > 0) {
        $('#roles').children().remove().end()
    }
});

(function () {
    let forms = document.querySelectorAll('.needs-validation')


    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }

                form.classList.add('was-validated')
            }, false)
        })
})()





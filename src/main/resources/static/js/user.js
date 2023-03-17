fillNavBar();
fillTableUser();
async function fillTableUser() {
    currentUser.then(user => {
        let mainTable = '';

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
                <td style="text-transform:uppercase">${roles}</td>`
        document.getElementById('tableUser').innerHTML = mainTable;

    }).catch(err => alert(err));
}
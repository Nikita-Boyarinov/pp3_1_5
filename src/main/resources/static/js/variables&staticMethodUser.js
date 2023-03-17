//url
const currentUserUrl = "/api/admin/currentUser";
const userUrl = "api/admin/users";


//other
const currentUser = fetch(currentUserUrl).then(response => response.json(), error => alert(`Error HTTP:  ${error.status}`));


//StaticMethod
async function fillNavBar(){
    currentUser.then(user => {
            let roles = '';
            user.roles.forEach(role => {
                roles += ' ';
                roles += role.name;
            })
            document.getElementById("navbar-email").innerHTML = user.email;
            document.getElementById("navbar-roles").innerHTML = roles;
            document.getElementById("navbar-email").setAttribute("idUser", user.id);
        }
    ).catch(err => alert(err));
}
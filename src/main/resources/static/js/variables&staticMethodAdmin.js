//url
const currentUserUrl = "/api/admin/currentUser";
const adminTableUrl = "/api/admin";
const getRolesUrl = "api/admin/roles";
const userUrl = "api/admin/users";

//form
const formEdit = document.forms.edit;
const formDelete = document.forms.delete;
const formNewUser = document.forms.newUser;


//modal
const modalEditBS = new bootstrap.Modal(document.getElementById('modalEdit'));
const modalEdit = document.getElementById('modalEdit');
const modalDeleteBS = new bootstrap.Modal(document.getElementById('modalDelete'));


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
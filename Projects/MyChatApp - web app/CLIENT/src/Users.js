import anna from './ContactsPhotos/anna.jpg';
import emma from './ContactsPhotos/emma.jpg';
import maria from './ContactsPhotos/maria.jpg';

let users = [
    {
        username: 'Anna',
        password: 'anna1234',
        picture:    anna
    },
    {
        username: 'Emma',
        password: 'emma1234',
        picture:    emma
    },
    {
        username: 'Maria',
        password: 'maria1234',
        picture:    maria
    },
    
];

export const setUsers = (newUsers) => {
    users = newUsers;
};

export default users;
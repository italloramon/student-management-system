import * as Fetch from '../utils/api';

const loginObj = {
	student: {
		success: "/student",
		error: "/login"
	}
};

const registerObj = {
	user: {
		success: "/login",
		error: "/register"
	}
};


export const reLogin = async (token) => {
	const response = await Fetch.get('/auth', token);

  	const username = response.username || "";
  	const role = response.role || "";

	const message = response.success
        ? "re-login feito com sucesso"
        : "sessão expirada";
  	const type = response.success ? "success" : "danger";
  	const route = response.success ? loginObj[role].success : loginObj[role].error;

  	console.log(role);

  	const success = response.success;

  	return { 
  		message, 
  		type, 
  		route, 
  		username, 
  		role, 
  		success 
  	};

};

const objLogin = {
	student: "/students/login",
	teacher: "/teachers/login",
	responsable: "/responsables/login"
}

export const login = async (data) => {
	data.login = data.username;
	const response = await Fetch.post(objLogin[data.type], data);
	if (response.role.match("TEACHER")) response.role = "TEACHER";

	return response;
};

export const register = async (data) => {
	const response = await Fetch.post("/auth/register", data);

	const { type: role } = data; 

    const message = response.message;
    const type = response.success ? "success" : "danger";
	const route = response.success ? registerObj[role].success : registerObj[role].error;

	const success = response.success;

    return { message, type, route, success };
};
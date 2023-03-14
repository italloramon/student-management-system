import * as Fetch from '../utils/api';

export const getAll = async () => {
	const response = await Fetch.get("/students/");

	return response;
}

export const getRanking = async () => {
	const response = await Fetch.get("/students/getRanking");

	return response;
};

export const getBoletim = async (id) => {
	const response = await Fetch.get(`/students/${id}`);

	return response;
};

export const sendScore = async (idStudent, idTeacher, bimester, score) => {
	const response = await Fetch.post(`/students/${idStudent}/${idTeacher}/${bimester}/${score}`, {})
	console.log(response)
}

export const getNotices = async () => {
	const response = await Fetch.get('/students/getNotices');
	return response;
}
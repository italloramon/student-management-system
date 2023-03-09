import axios from 'axios';

const BASE_URL = "http://localhost:8080/api" ;

export const api = axios.create({
	baseURL: BASE_URL
});

export const get = async (url, token) => {
	try {
		const response = await api.get(url);
		response.data.type = 'success';
		return response.data;
	} catch (error) {
		console.log(error);

		error.response.data.type = 'danger';
		return error.response.data;
	}
};

export const post = async (url, data, token=null) => {
	try {
		const response = await api.post(url, data);
		response.data.type = 'success';
		return response.data;
	} catch (error) {
		error.response.data.type = 'danger';
		return error.response.data;
	}
};

export const patch = async (url, data, token=null) => {
	try {
		const response = await api.patch(url, data);
		response.data.type = 'success';
		return response.data;
	} catch (error) {
		error.response.data.type = 'danger';
		return error.response.data;
	}
};

export const remove = async (url, token) => {
	try {
		const response = await api.delete(url);
		response.data.type = 'success';
		return response.data;
	} catch (error) {
		error.response.data.type = 'danger';
		return error.response.data;
	}
};
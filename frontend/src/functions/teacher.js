import * as Fetch from '../utils/api';

export const getSalary = async (id) => {
	const response = await Fetch.get(`/teachers/${id}`);

	return response.salary;
};

export const sendNotice = async (data) => {
	const response = await Fetch.post(`/teachers/sendNotice`, data);

	return response;
};
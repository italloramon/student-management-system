import * as Fetch from '../utils/api';

export const getPayment = async (id) => {
	const response = await Fetch.get(`/responsables/${id}/total`);

	const values = Object.values(response);
  	const keys = Object.keys(response);
  	values.pop(); keys.pop();

  	const index = keys.indexOf("Total to pay:");

  	keys.splice(index, 1);
  	values.splice(index, 1);

  	const result = [];
  	for (let i = 0; i < keys.length; i++) {
    	result.push({ name: keys[i], value: values[i] });
  	}

	return result;
};

export const getStudents = async (id) => {
	const response = await Fetch.get(`/responsables/${id}/total`);

	const values = Object.values(response);
  	const keys = Object.keys(response);
  	values.pop(); keys.pop();

  	const index = keys.indexOf("Total to pay:");

  	keys.splice(index, 1);
  	values.splice(index, 1);

  	const result = [];
  	for (let i = 0; i < keys.length; i++) {
    	result.push({ name: keys[i], value: values[i] });
  	}

	return result;
}


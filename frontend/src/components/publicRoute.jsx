import { useNavigate, Outlet } from 'react-router-dom';
import { useAuth } from '../context';
import { useEffect } from 'react'; 
import { Layout } from './layout';
import { Loading } from './loading';

const items = [
	{
		role: 'STUDENT',
		link: '/student'
	},
	{
		role: 'TEACHER',
		link: '/teacher'
	},
	{
		role: 'admin',
		link: '/admin'
	},
	{
		role: 'RESPONSABLE',
		link: '/responsable'
	}
];

export const PublicRoute = () => {
	const navigate = useNavigate();
	const { token, role, loading } = useAuth();

	useEffect(() => {
		if (token && role) {
			const item = items.find(item => item.role === role);
			navigate(item.link);
			console.log(item.link);
		};
	}, [token, role]);

	return (
		<Layout>
			{loading ? <Loading /> : <Outlet />}
		</Layout>
	)}
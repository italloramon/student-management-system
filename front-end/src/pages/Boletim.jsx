import { Table } from 'react-bootstrap';
import { useData } from '../hooks/useData';
import { useState } from 'react';

const Boletim = () => {
	const { data, id } = useData();

	const [notas, setNotas] = useState(null);

	return (
		<center style={{ padding: '2em' }}>
			<h2>Boletim</h2>
			<Table striped bordered hover variant="light" style={{ margin: "1em 0" }}>
		      <thead>
		        <tr>
		          <th>#</th>
		          <th>Inglês</th>
		          <th>Geografia</th>
		          <th>História</th>
		          <th>Matemática</th>
		          <th>Português</th>
		        </tr>
		      </thead>
		        {data && 
		      <tbody>
		              <tr>
		                <td>AB1</td>
		                <td>{data.find(item => item.id === id).english.score1}</td>
		                <td>{data.find(item => item.id === id).geography.score1}</td>
		                <td>{data.find(item => item.id === id).history.score1}</td>
		                <td>{data.find(item => item.id === id).mathematics.score1}</td>
		                <td>{data.find(item => item.id === id).portuguese.score1}</td>
		              </tr>
		              <tr>
		                <td>AB2</td>
		                <td>{data.find(item => item.id === id).english.score2}</td>
		                <td>{data.find(item => item.id === id).geography.score2}</td>
		                <td>{data.find(item => item.id === id).history.score2}</td>
		                <td>{data.find(item => item.id === id).mathematics.score2}</td>
		                <td>{data.find(item => item.id === id).portuguese.score2}</td>
		              </tr>
		              <tr>
		                <td>AB3</td>
		                <td>{data.find(item => item.id === id).english.score3}</td>
		                <td>{data.find(item => item.id === id).geography.score3}</td>
		                <td>{data.find(item => item.id === id).history.score3}</td>
		                <td>{data.find(item => item.id === id).mathematics.score3}</td>
		                <td>{data.find(item => item.id === id).portuguese.score3}</td>
		              </tr>
		              <tr>
		                <td>AB4</td>
		                <td>{data.find(item => item.id === id).english.score4}</td>
		                <td>{data.find(item => item.id === id).geography.score4}</td>
		                <td>{data.find(item => item.id === id).history.score4}</td>
		                <td>{data.find(item => item.id === id).mathematics.score4}</td>
		                <td>{data.find(item => item.id === id).portuguese.score4}</td>
		              </tr>
		      </tbody>
		          }
		    </Table>
		</center>
	)
};

export default Boletim;
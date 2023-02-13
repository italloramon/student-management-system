import { useParams, useNavigate } from "react-router-dom";

const SelectedStudent = () => {
  let params = useParams();

  return <h1>{params.id}</h1>;
};

export default SelectedStudent;

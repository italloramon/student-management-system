import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { Button } from "react-bootstrap";
import { StyledButton } from "../components/UI/Button";

const PageError = () => {
  const navigate = useNavigate();
  return (
    <main>
      <h1>404 - Page not Found</h1>
      <iframe
        src="https://i.pinimg.com/originals/a3/3e/23/a33e23328809a536a601c95b5ae23953.gif"
        width="498"
        height="488"
        frameBorder="0"
        allowFullScreen
      ></iframe>

      <StyledButton color="blue" className="" onClick={() => navigate("/")}>
        Go home
      </StyledButton>
    </main>
  );
};

export default PageError;

import { useFormik } from 'formik'
import React, { useCallback, useState } from 'react'
import styled from 'styled-components';
import MainComponent from '../../components/UI/MainComponent';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { endPoint, path } from '../../constant/EndPoint';
import { ResponseModel } from '../../model/ResponseModel';
import { LoginResponse } from '../../model/LoginModel';


function Login() {
 let token  = localStorage.getItem("token-cms")
  const [isUserLogin,setUserLogin] = useState(token==null?false:true);
   
  //
  const navigate = useNavigate();
  React.useEffect(() => {
    if (token==null || isUserLogin==false ) {
      setUserLogin(false)
      navigate("login")
    }
    else{
      setUserLogin(true)
    }
  }, [token])
  console.log("Login Component")
  const formik = useFormik({
    initialValues: {
      email: '',
      password: ''
    },
    onSubmit: (val) => {
      console.log("handleSubmit")
      LoginHandler(val)
    }
  })
  const LoginHandler = useCallback((loginRequest: any) => {
    (async () => {
      try {
        let url = endPoint.cms + path.cms.login;
        let res = await axios.post(url, loginRequest, {})
        let data = res.data as typeof LoginResponse;
        console.log(data)
        if (data.success) {
          localStorage.removeItem("token-cms");
          let token = data.data["token"];      
          localStorage.setItem("token-cms", token)
          setUserLogin(true)
          navigate("/")
          return;
        }
      }
      catch (err) {
        console.error("Login Creditional Erro=", err)
      }
    })()
  }, [])
  return (
    <>
      {isUserLogin && <MainComponent />}
      {!isUserLogin && <DivContainer><FormContainer>
        <form onSubmit={formik.handleSubmit}>
          <FormGroup>
            <label>Email</label>
            <input
              value={formik.values.email}
              onChange={formik.handleChange}
              name="email"
              type='text' />
          </FormGroup>

          <FormGroup>
            <label>Password</label>
            <input
              value={formik.values.password}
              onChange={formik.handleChange}
              name="password"
              type='text' />
          </FormGroup>

          <FormGroup>
            <label>Login</label>
            <input type='submit' />
          </FormGroup>
        </form>


      </FormContainer>
      </DivContainer>}
    </>
  )
}
const DivContainer = styled.div`
  width: 100%;
  background-color:lightblue;
  box-sizing: border-box;
  position:relative;

`;
const FormContainer = styled.div`
  
  max-width: 400px;
  margin: 10px auto;
  padding: 20px;
  border: 1px solid #ccc;
  background-color:blue;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
`;

const FormGroup = styled.div`
  margin-bottom: 15px;

  label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
  }

  input {
    width: 100%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 3px;
    box-sizing: border-box;
  }

  input[type='submit'] {
    background-color: #3498db;
    color: white;
    cursor: pointer;
  }
`;


export default Login
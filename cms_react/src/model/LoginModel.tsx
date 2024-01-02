import { ResponseModelDataType } from "./ResponseModel";


// Login Request
type LoginInterface={
    email: string|null,
    password: string|null
}
const LoginRequest:LoginInterface={
    email: '',
    password: ''
}

// Login Response
const LoginResponse:ResponseModelDataType={
    statusCode: null,
    message: null,
    success: null,
    data: null
}

export {LoginRequest,LoginResponse};




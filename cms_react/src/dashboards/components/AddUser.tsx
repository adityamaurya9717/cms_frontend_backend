import React , {useState} from 'react';
import axios from "axios";
import RestTemplate  from '../../service/RestTemplate';
import { Formik, ErrorMessage, Field, Form } from 'formik';
import { Routes, useNavigate } from 'react-router-dom';
import './css/adduser.css'
import AlertDialogSlide from '../../common/AlertDialog';
const AddUser = () => {
    const navigate = useNavigate()
    const [dialogToogle,setDialogToogle] = useState(false);
     const alertDialog = {
        message:"successfully added"
     }
    const initValue = {
        firstName: '',
        lastName: '',
        email: '',
        phone: '',
        state:'',
        gender:'',
    }
    const onCloseDialogHandler = ()=>{
        console.warn("dialog close called")
        setDialogToogle(false)

    }
    const url = "http://localhost:8080/api/customer/add-user";
    const onSubmit = async (values:any)=>{
        console.log("submit value",values)
        setDialogToogle(true)
 
       let result =  await axios.post(url,values,{}).then(response=>{
          return response.data;
       })
       .catch(error => {
        // Handle the error here
        console.log("Error",error
       )
      });
      console.log("out",result)
       
    }


    return (
        <>
        {console.log("adduser componet load")}
            <div>
              <AlertDialogSlide onCloseHandler={onCloseDialogHandler} isOpen={dialogToogle} data={alertDialog} /> 
                <h2>Add User</h2>
                <Formik
                    initialValues={initValue}
                    onSubmit={(values:any) => {
                        onSubmit(values)
                    }}>
                    <Form className="form_add_user">
                        <div>
                            <label htmlFor="firstName">FirstName :</label>
                            <Field className="form_Feild" type="text" id="firstName" name="firstName" />
                            <ErrorMessage name="firstName" component="div" />
                        </div>

                        <div>
                            <label htmlFor="lastName">LastName :</label>
                            <Field className="form_Feild" type="text" id="lastName" name="lastName" />
                            <ErrorMessage name="lastName" component="div" />
                        </div>

                        <div>
                            <label htmlFor="email">Email :</label>
                            <Field className="form_Feild" type="text" id="email" name="email" />
                            <ErrorMessage name="email" component="div" />
                        </div>

                        <div>
                            <label htmlFor="phone">phone :</label>
                            <Field className="form_Feild" type="number" id="phone" name="phone" />
                            <ErrorMessage name="phone" component="div" />
                        </div>
                        {/* <div>
                            <label htmlFor="dateOfBirth">D.O.B :</label>
                            <Field  className="form_Feild" type="date" id="dateOfBirth" name="dateOfBirth" />
                            <ErrorMessage name="dateOfBirth" component="div" />
                        </div>
                        
                        <div>
                            <label htmlFor="address">Address :</label>
                            <Field  className="form_Feild" type="text" id="address" name="address" />
                            <ErrorMessage name="address" component="div" />
                        </div> */}

                        <div>
                            <label htmlFor="gender">Gender :</label>
                            <Field as="select" className="form_Feild" type="text" id="gender" name="gender" >
                             
                                <option value="male">Male</option>
                                <option value="female">Female</option>
                                <option value="other">other</option>
                               
                            </Field>
                            <ErrorMessage name="gender" component="div" />
                        </div>

                        {/* <div>
                            <label htmlFor="city">city :</label>
                            <Field className="form_Feild" type="text" id="city" name="city" />
                            <ErrorMessage name="city" component="div" />
                        </div> */}

                        <div>
                            <label htmlFor="state">Office :</label>
                            <Field className="form_Feild" type="text" id="state" name="state" />
                            <ErrorMessage name="state" component="div" />
                        </div>

                        {/* <div>
                            <label htmlFor="pincode">pincode :</label>
                            <Field className="form_Feild" type="text" id="pincode" name="pincode" />
                            <ErrorMessage name="pincode" component="div" />
                        </div> */}
                       



                        <button className="form-submit-btn" type="submit">Submit</button>

                    </Form>
                </Formik>

            </div>


        </>
    )
}
export default AddUser; 

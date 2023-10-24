
import { Button, TextField } from '@mui/material'
import axios from 'axios';
import * as Yup from 'yup';
import { Form, Formik, useFormik, FormikHelpers } from 'formik'
import React, { useState, useEffect, useCallback } from 'react'
import { endPoint, path } from '../../../constant/EndPoint'
import '../css/addbrand.css'
import ShowBrand from './ShowBrand';

let addBrand = {
    brandName: '',
    brandDescription: ''
}
const Brand =  () => {

  
const validationSchema = Yup.object({
  brandName : Yup.string().required("Brand Name is Required"),
  brandDescription : Yup.string().required("Brand Description is Required")

})

   

     const formik = useFormik({
          initialValues: {
            brandName: '',
            brandDescription: ''
          },
          validationSchema:validationSchema,
          onSubmit: (values) => {
            console.log(values)
            addBrand = {...values}
            alert(JSON.stringify(values, null, 2));
           // addBrandSubmit(values)
          }
        });
    
    


    const addBrandSubmit = async (values: any) => {
        console.log("submit=", values)
    }
    const reset = ()=>{
        formik.resetForm();
    }

    useEffect( ()=>{
     const url = endPoint.cms + path.cms.addbrand;
     axios.post(url,addBrand,{})
      .then(res=>{
        return res.data;
      })
      .catch(err=>{
        console.error(err)
      })


    },[])


    return (
        <div className='add-brand-form-container'>
            <form onSubmit={formik.handleSubmit}  >

                <div>
                    <TextField variant='standard' label="Brand Name" id="brandName"
                                name="brandName"
                                onBlur={formik.handleBlur}
                                onChange={formik.handleChange} 
                                value={formik.values.brandName}
                                error={formik.touched.brandName}
                                required 
                                helperText={formik.errors.brandName} ></TextField>
                </div>
                  
                <div>
                    <TextField style={{width:'300px'}} variant='outlined' label="BrandDescription" id="brandDescription"
                                name="brandDescription"
                                onBlur={formik.handleBlur}
                                onChange={formik.handleChange}
                                value={formik.values.brandDescription}
                                error={formik.touched.brandDescription}
                                helperText={formik.errors.brandDescription}
                                required 
                                multiline
                                rows={4}   ></TextField>
                </div>
                <Button className="form-submit-btn" variant='contained' type="submit">Submit</Button>
                <Button onClick={reset}  variant='outlined' type="button" color='secondary'>Reset</Button>


            </form>
            <ShowBrand />
        </div>
    )
}



export default Brand

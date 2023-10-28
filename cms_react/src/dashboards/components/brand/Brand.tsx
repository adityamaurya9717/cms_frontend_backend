
import { Button, TextField } from '@mui/material'
import axios from 'axios';
import * as Yup from 'yup';
import { Form, Formik, useFormik, FormikHelpers } from 'formik'
import React, { useState, useEffect, useCallback, SyntheticEvent } from 'react'
import { endPoint, path } from '../../../constant/EndPoint'
import '../css/addbrand.css'
import ShowBrand from './ShowBrand';
import { type } from 'os';
import CustomSnackBar from '../../../common/CustomSnackBar';

type AddBrandRequest={
  brandName: string,
  brandDescription: string,
  brandCountry: string,

}
let addBrand:AddBrandRequest = {
    brandName: '',
    brandDescription: '',
    brandCountry:'test'
}
let updateBrandRequest = {
  id:'',
  brandId:'',
  brandName: '',
  description: '',
  brandCountry:''
}
const Brand =  () => {

  
const validationSchema = Yup.object({
  brandName : Yup.string().required("Brand Name is Required"),
  brandDescription : Yup.string().required("Brand Description is Required")

})
   const data = {
    open:false,
    alertType:'success',
    message:'Added Success Fully'
   }
   const [snackBarData,setSnackBar] = useState({...data});
   const [updateData,setUpdateData] = useState<any>({
     showUpdate:false,
     payload:{...updateBrandRequest}
   });


     const formik = useFormik({
          initialValues: {
            brandName: '',
            brandDescription: ''
          },
          validationSchema:validationSchema,
          onSubmit: (values) => {
            console.log(values)
            addBrand = {...values,brandCountry:'test'}
            addBrandSubmit(values)
          }
        });
    
    
    const openOrCloseSnakBar = (isOpen:boolean)=>{
      if(isOpen){
        setSnackBar(prev=>{return {...prev,open:true,}})
      }
      else{
        setSnackBar(prev=>{return {open:true,alertType:"error",message:"SomeThing Went Wrong"}})
      }
      // to set snackBar on Default Message
      setTimeout(()=>{
        setSnackBar(pre=>{return {...data} })
       },2000)  
    }
    // HTTP REQUEST ADD-BRAND
    const addBrandSubmit = async (values: any) => {
        console.log("submit=", values)
        let url = endPoint.cms + path.cms.addbrand
        axios.post(url,addBrand,{})
        .then(res=>{
          openOrCloseSnakBar(true)
          return res.data
        })
        .catch(err=>{
          openOrCloseSnakBar(false)
          console.error(err)
        })
    }
    // HTTP REQUEST Update-BRAND
    const updateBrandSubmit = async (requestPayload: any) => {
      console.log("update Brand PAyload =", requestPayload)
      let url = endPoint.cms + path.cms.updateBrand
      axios.put(url,requestPayload,{})
      .then(res=>{
        openOrCloseSnakBar(true)
        return res.data
      })
      .catch(err=>{
        openOrCloseSnakBar(false)
        console.error(err)
      })
      reset()
  }
   // Reset FORM
    const reset = ()=>{
        formik.resetForm();
        setUpdateData({
          showUpdate:false,
          payload:{...updateBrandRequest}
         })
    }

  // Update Brand Form from List from Child Component
   const updateBrandHandler = (brandData:any)=>{
     console.log("form list",brandData);
    // formik.initialValues.brandName = brandData.brandName;
     formik.setFieldValue('brandName',brandData.brandName)
     formik.setFieldValue('brandDescription',brandData.brandDescription)
     //formik.setValues({'brandName':brandData.brandName,'brandDescription':brandData.brandDescription});
     setUpdateData({
      showUpdate:true,
      payload:{...brandData}
     })

   }

   const updateBrand=(e:any)=>{
     console.log( "update Payload data",updateData.payload)
     updateBrandSubmit({...updateData.payload,brandName:formik.values.brandName,description:formik.values.brandDescription})

   }


    return (
        <div className='add-brand-form-container'>
         { snackBarData.open && <CustomSnackBar data={snackBarData}/>}
            <form onSubmit={formik.handleSubmit}  >

                <div>
                    <TextField variant='standard' label="Brand Name" id="brandName"
                                disabled={updateData.showUpdate?true:false}
                                name="brandName"
                                onBlur={formik.handleBlur}
                                onChange={formik.handleChange} 
                                value={formik.values.brandName}
                                error={formik.touched.brandName}
                                required 
                                helperText={updateData.showUpdate==false && formik.errors.brandName} ></TextField>
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
                { updateData.showUpdate==false ?   <Button className="form-submit-btn" variant='contained' type="submit">Submit</Button>
                : <Button onClick={updateBrand} className="form-submit-btn" variant='contained' type="button">Update</Button>
                }

                <Button onClick={reset}  variant='outlined' type="button" color='secondary'>Reset</Button>


            </form>
            <ShowBrand onUpdateBrand={updateBrandHandler} />
        </div>
    )
}



export default Brand

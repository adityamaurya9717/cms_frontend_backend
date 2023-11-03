import { Button, FormControl, InputLabel, MenuItem, Select, TextField } from '@mui/material';
import { useFormik } from 'formik';
import React, { useState } from 'react'
import * as Yup from 'yup';
import './addcategory.css'
import ShowCategory from './ShowCategory';
import { endPoint, path } from '../../../constant/EndPoint';
import axios from 'axios';
import AlertDialogSlide from '../../../common/AlertDialog';
import CustomSnackBar from '../../../common/CustomSnackBar';

type AddCategoryRequest = {
    categoryName: string,
    categoryDescription: string,
    categoryLevel: string | null,

}
let addCategory: AddCategoryRequest = {
    categoryName: '',
    categoryDescription: '',
    categoryLevel: ''
}
function AddCategory(props: any) {

    const data = {
        open:false,
        alertType:'success',
        message:'Added Success Fully'
       }
       const [snackBarData,setSnackBarData] = useState({...data});

       const openOrCloseSnakBar = (isOpen:boolean)=>{
        if(isOpen){
            setSnackBarData(prev=>{return {...prev,open:true,}})
        }
        else{
            setSnackBarData(prev=>{return {open:true,alertType:"error",message:"SomeThing Went Wrong"}})
        }
        // to set snackBar on Default Message
        setTimeout(()=>{
            setSnackBarData(pre=>{return {...data} })
         },2000)  
      }   

    const validationSchema = Yup.object({
        categoryName: Yup.string().required("Category Name is Required"),
        categoryDescription: Yup.string().required("Category Description is Required"),
        categoryLevel: Yup.string().required("Category Description is Required")
    })

    const formik = useFormik({
        initialValues: { 
            categoryName: '',
            categoryDescription: '',
            categoryLevel: ''
         },
        //validationSchema: validationSchema,
        onSubmit: (values) => {
            console.log(values)
            addCategory = { ...values }
            addCategoryInDB()
        }
    });


  // Add Category In DB
    const addCategoryInDB= async ()=>{
      let url = endPoint.cms + path.cms.addcategory;
       try{
          let response = await axios.post(url,addCategory,{})
          let data = response.data
          if(data["success"]===true){
            openOrCloseSnakBar(true)
            reset()
           }
           else{
            openOrCloseSnakBar(false)

           }
       }
       catch(err){
        console.log("AddCategory Error=",err)
        openOrCloseSnakBar(false)

            
       }
    }

    const reset = ()=>{
        formik.resetForm()
    }

    return (
        <div className='add-category-form-container'> 
           {  snackBarData.open==true &&  <CustomSnackBar data={snackBarData} /> }
            <form onSubmit={formik.handleSubmit}  >

            <div>
                <TextField variant='outlined' label="category Name" id="categoryName"
                    name="categoryName"
                    onBlur={formik.handleBlur}
                    onChange={formik.handleChange}
                    value={formik.values.categoryName}
                   // error={formik.touched.categoryName}
                     >
                        
                 </TextField>
            </div>

            <div>
                <TextField style={{ width: '300px' }} variant='outlined' label="Category Description"
                    id="categoryDescription"
                    name="categoryDescription"
                    onBlur={formik.handleBlur}
                    onChange={formik.handleChange}
                    value={formik.values.categoryDescription}
                   // error={formik.touched.categoryDescription}
                   // helperText={formik.errors.categoryDescription}
                    multiline
                    rows={4}   ></TextField>
            </div>
            <div>
                <FormControl  sx={{ m: 0, minWidth: 250 }}>
                    <InputLabel id="demo-simple-select-autowidth-label">Category Level</InputLabel>
                    <Select
                        onChange={formik.handleChange}
                        name='categoryLevel'
                        value={formik.values.categoryLevel}
                        labelId="demo-simple-select-autowidth-label"
                        id="demo-simple-select-autowidth"
                        label=" Category Level"
                    >
                        <MenuItem value="None"> <em>None</em></MenuItem>
                        <MenuItem value="LEVEL_ONE">Level One</MenuItem>
                        <MenuItem value="LEVEL_TWO"> Level Two</MenuItem>
                        <MenuItem value="LEVEL_THREE"> Level Three</MenuItem>


                    </Select>
                </FormControl>
            </div>
            <Button variant='contained' type="submit" color='primary'>Add</Button>
            <Button onClick={reset}  variant='outlined' type="button" color='secondary'>Reset</Button>


        </form>
        <ShowCategory />
        
        </div>
    )
}

export default AddCategory

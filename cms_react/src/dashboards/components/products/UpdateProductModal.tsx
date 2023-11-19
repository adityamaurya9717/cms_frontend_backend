import { Box, Button, FormControl, InputLabel, MenuItem, Modal, Select, TextField, Typography } from '@mui/material';
import axios from 'axios';
import { useFormik } from 'formik';
import React, { useState,useEffect, useRef, useCallback } from 'react'
import * as Yup from 'yup';

import { endPoint, path } from '../../../constant/EndPoint';
import BrandService from '../../../service/BrandService';
import CustomSnackBar from '../../../common/CustomSnackBar';
const MenuProps = {
    PaperProps: {
        style: {
            maxHeight: 200,
            width: 200,
        },
    },
};
const style = {
    position: 'absolute' as 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: '80%',
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
};
const boxStyle = {
    width: '80%',
    margin: '40px auto',
    display: 'grid',
    gridTemplateColumns: '33% 33% 33%',
    rowGap: '50px'
}
interface UpdateProductRequestInterface {
    id:string,
    productId: string,
    brandId: string;
    productPrice: {
        taxPercentage: number,
        sellingPrice: number,
        mrp: number
    }

}
const UpdateProductRequest: UpdateProductRequestInterface = {
    id:'',
    productId: '',
    productPrice: {
        taxPercentage: 0,
        sellingPrice: 0,
        mrp: 0
    },
    brandId: ''
}
function UpdateProductModal(props: any) {
    console.log("model status")
    const isOpen = props.data.isOpen;
    const [brands,setBrands] = useState([])
    const productData = props.data.productData
    console.log("product data=",productData)
    const data = {
        open:false,
        alertType:'success',
        message:'Added Success Fully'
       }
    const [snackBarData,setSnackBar] = React.useState({...data});
    const validationSchema = Yup.object({
        brandName: Yup.number().required("Brand Name is Required"),
        productPrice: Yup.object({
            taxPercentage: Yup.string().required(""),
            sellingPrice: Yup.number().required("selling Name is Required")
        })

    })
    
    useEffect(()=>{
        ( async ()=>{
        let res = await BrandService.getAllBrand();
        //console.log("get All brand",res)
        setBrands(res)
        //console.log(productData.brandId)
        formik.setFieldValue("brandId",productData.brandId)
        })()

    },[])

    const successOrfaliedSnakBar = useCallback( (isSuccess:boolean,message:string)=>{
        if(isSuccess){
          setSnackBar(prev=>{return {...prev,alertType:"success",open:true,message:"Success Fully Added"}})
        }
        else{
          setSnackBar(prev=>{return {open:true,alertType:"error",message:message}})
        }
        // to set snackBar on Default Message
        setTimeout(()=>{
          setSnackBar(pre=>{return {...data} })
         },2000)  
      },[])



    const formik = useFormik({
        initialValues: {...UpdateProductRequest,id:productData.id,
            productPrice:{
               mrp:productData.productPrice?.mrp,
               sellingPrice:productData.productPrice?.sellingPrice,
               taxPercentage:productData.productPrice?.taxPercentage

            }
        },
        onSubmit: (value) => {
            console.log("formik Submit=", value)
            updateProductDetail(value)

        }
    })
    // const closeModelHandler = () => {
    //     props.onCloseModelHandler(false)
    // }
    const closeModelHandler= useCallback(()=>{
        props.onCloseModelHandler(false)
    },[]);
    // for update PRoduct Detail
    const updateProductDetail = (updateProductDetail: any) => {
        if (formik)
            console.log("updateProduct Detail=", updateProductDetail);
        (async () => {
            try {
                let url = endPoint.cms + path.cms.updateProduct ;
                let response = await axios.post(url, updateProductDetail, {})
                let data = response.data;
                if(data.success==true){
                    successOrfaliedSnakBar(true,data.message)
                }
                else{
                    successOrfaliedSnakBar(false,data.message) 
                }
            }
            catch (err) {
                console.error("Update Product Error =", err)
                successOrfaliedSnakBar(false,"Some Thing went Wrong") 
            }
        })()
    }

    return (
        <div>
           { snackBarData.open && <CustomSnackBar data={snackBarData}/>}

            <Modal
                open={isOpen}
                onClose={closeModelHandler}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description">


                <form onSubmit={formik.handleSubmit}>

                    <Box sx={style} >
                        <Typography id="modal-modal-title" variant="h6" component="h1">
                            Update Product
                        </Typography>
                        <Typography id="modal-modal-description" component="div" sx={{ mt: 2 }}>
                            <Box component="form" sx={boxStyle}>
                                <div>
                                    <TextField disabled
                                        value={productData.id}
                                        name="productId" label="Product Id" variant="outlined"></TextField>
                                </div>

                                <div>
                                    <TextField disabled
                                        value={productData.productName}
                                        name="productName" label="Product Name" variant="outlined"></TextField>
                                </div>
                                <div>
                                    <TextField disabled
                                        value={productData.description}
                                        name="productDescription" label="Product Description" variant="outlined"></TextField>
                                </div>
                                <div>
                                    <TextField
                                        value={productData.categoryCode}
                                        name="categoryId" disabled label="categoryId" variant="outlined"></TextField>
                                </div>
                                <div>
                        <FormControl fullWidth sx={{ m: 0, width: 200 }}>
                            <InputLabel id="demo-simple-select-autowidth-label">Brand</InputLabel>

                            <Select
                                labelId="demo-simple-select-autowidth-label"
                                id="demo-simple-select-autowidth"
                                label="Brands"
                                name="brandId"
                                value={formik.values.brandId}
                                onChange={formik.handleChange}
                                MenuProps={MenuProps}

                            >
                                {
                                    brands.map((data: any, index: number) => {
                                        return <MenuItem  key={index} value={data.brandId}>{data.brandName}</MenuItem>

                                    })
                                }

                            </Select>
                        </FormControl>
                    </div>
                            </Box>
                            <div>
                                Price Info
                            </div>
                            <Box component="div" sx={boxStyle}>
                                <div>
                                    <TextField
                                        value={formik.values.productPrice.mrp}
                                        onChange={formik.handleChange}
                                        title='mrp ' type='number' name="productPrice.mrp" label="MRP" variant="outlined"></TextField>
                                </div>
                                <div>
                                    <TextField
                                        value={formik.values.productPrice.taxPercentage}
                                        onChange={formik.handleChange}
                                        title='product tax' type='number' name="productPrice.taxPercentage" label="Tax" variant="outlined"></TextField>
                                </div>
                                <div>
                                    <TextField
                                        value={formik.values.productPrice.sellingPrice}
                                        onChange={formik.handleChange}
                                        type='number' name="productPrice.sellingPrice" label="Selling Price" variant="outlined"></TextField>
                                </div>

                            </Box>
                            {/* Product Attribute */}
                            <Box>

                                <div>
                                    <TextField type="text" multiline rows="3"   name="productTag" variant='standard'> </TextField>
                                </div>

                            </Box>
                        </Typography>
                        <Box>
                            {/* <Button onClick={() => updateProductDetail(formik.values)} type="button" variant="contained">Update</Button> */}
                            <Button type="submit" variant="contained">Update</Button>

                            <Button onClick={closeModelHandler}>Close</Button>
                        </Box>

                    </Box>
                </form>
            </Modal>
        </div>
    )
}

export default UpdateProductModal
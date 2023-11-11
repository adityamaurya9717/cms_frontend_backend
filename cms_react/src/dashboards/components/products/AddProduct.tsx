import React, { useState, useReducer, useEffect } from 'react';
import axios from 'axios';
import Box from '@mui/material/Box'
import TextField from '@mui/material/TextField'
import { Button, FormControl, MenuItem, Paper, Select, SelectChangeEvent } from '@mui/material';
import InputLabel from '@mui/material/InputLabel';
import { endPoint, path } from '../../../constant/EndPoint';
import { useFormik } from 'formik';
import CloudUploadIcon from '@mui/icons-material/CloudUpload';

let brandDetail: any = [{
    brandId: 'asas',
    brandName: 'sdsd'
}]
let categoryDetail: any = [{
    categoryName: '',
    categoryId: ''
}]

const MenuProps = {
    PaperProps: {
        style: {
            maxHeight: 200,
            width: 200,
        },
    },
};

const AddProduct = () => {
    const input = {
        productName: '',
        categoryId: '',
        productDescription: '',
        brandId: '',
        productPrice: {
            mrp: null,
            taxPercentage: null,
            price: null,
            sellingPrice: null
        }
    }
    const [productForm, setProductForm] = useState(input)
    // fetch data from API
    const [brand, setBrands] = useState(brandDetail)
    // fetch Data From API
    const [category, setCategory] = useState(categoryDetail)
    const formik = useFormik({
        initialValues: {
            productName: '',
            categoryId: '',
            productDescription: '',
            brandId: '',
            productPrice: {
                mrp: '',
                taxPercentage: '',
                price: '',
                sellingPrice: ''
            }
        },
        onSubmit: (value) => {
            console.log("formik Submit=", value)
        }
    })

    // const setProductFormDetail = (e:any)=>{setProductForm(preState=>{return {...preState, [e.target.name]:e.target.value}})}
    // const setProductCategory = (e:any)=>{setProductForm(preState=>{  
    //         return {...preState, productCategory:e.target.value}}
    //         )}


    const onSubmit = () => {
        console.log(productForm)
    }
    // get All Brand
    useEffect(() => {
        console.log("use effect Caleed")
        let url = endPoint.cms + path.cms.getAllBrands;
        let res = axios.get(url, {})
            .then(res => {
                console.log("brandList", res)
                setBrands([...res.data])
                return res.data
            })
            .catch(err => {
                console.error(err)
            })
    }, [])
    // get ALL LEVEL THREE CAtegory
    useEffect(() => {
        console.log("use effect Caleed")
        let url = endPoint.cms + path.cms.getALLCategory + "?categoryLevel=LEVEL_THREE";
        let res = axios.get(url, {})
            .then(res => {
                console.log("categoryList", res)
                setCategory([...res.data])
                return res.data
            })
            .catch(err => {
                console.error(err)
            })
    }, [])

    const clearForm = () => {
        console.log("clear Form")
        formik.resetForm();
    }
    const [selectedFile, setSelectedFile] = useState<any>(null);

      const handleFileChange = (event:any) => {
        console.log(event.target.files[0])
        setSelectedFile(event.target.files[0]);
      };
    const uploadFile = (event: any) => {
        const formData = new FormData();
        formData.append('file', event.target.files[0]);
        let url = endPoint.cms + path.cms.uploadFile
        axios.post(url, formData)
            .then(res => {
                console.log(res)
            }).catch(err => {
                console.error(err)
            })
    }

    return (
        <div>
            <h3>Product Catalog</h3>
            <form onSubmit={formik.handleSubmit}>
                <Box component="form" sx={boxStyle}>

                    <div>
                        <TextField name="productName" value={formik.values.productName} onChange={formik.handleChange} label="Product Name" variant="outlined"></TextField>
                    </div>
                    <div>
                        <TextField name="productDescription" value={formik.values.productDescription} onChange={formik.handleChange} label="Product Description" variant="outlined"></TextField>
                    </div>
                    <div>
                        <TextField name="categoryId" value={formik.values.categoryId} onChange={formik.handleChange} label="categoryId" variant="outlined"></TextField>
                    </div>
                    <div>
                        <label htmlFor="file-upload">
                            <input
                                type="file"
                                id="file-upload"
                                style={{ display: 'none' }}
                                onChange={uploadFile}
                            />
                            <Button
                                variant="contained"
                                component="span"
                                startIcon={<CloudUploadIcon />}
                            >
                                Upload File
                            </Button>
                        </label>
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
                                    brand.map((data: any, index: number) => {
                                        return <MenuItem key={index} value={data.brandId}>{data.brandName}</MenuItem>

                                    })
                                }

                            </Select>
                        </FormControl>
                    </div>
                    <div>
                        <FormControl sx={{ m: 0, minWidth: 250 }}>
                            <InputLabel id="demo-simple-select-autowidth-label">Product Category</InputLabel>
                            <Select
                                onChange={formik.handleChange}
                                value={formik.values.categoryId}
                                labelId="demo-simple-select-autowidth-label"
                                id="demo-simple-select-autowidth"
                                autoWidth
                                name="categoryId"
                                label="Product Category"
                            >
                                <MenuItem value="">
                                    <em>None</em>
                                </MenuItem>
                                {
                                    category.map((data: any, index: number) => {
                                        return <MenuItem value={data.categoryName}>{data.categoryName}</MenuItem>
                                    })
                                }
                            </Select>
                        </FormControl>
                    </div>
                    <div>

                    </div>
                </Box>
                <div>
                    Price Info
                </div>
                <Box component="div" sx={boxStyle}>
                    <div>
                        <TextField value={formik.values.productPrice.mrp} title='mrp ' type='number' name="productPrice.mrp" onChange={formik.handleChange} label="MRP" variant="outlined"></TextField>
                    </div>
                    <div>
                        <TextField title='product tax' type='number' name="productPrice.taxPercentage" onChange={formik.handleChange} label="Tax" variant="outlined"></TextField>
                    </div>
                    <div>
                        <TextField type='number' name="productPrice.sellingPrice" onChange={formik.handleChange} label="Selling Price" variant="outlined"></TextField>
                    </div>

                </Box>
                <Box component="div" sx={boxSubmitStyle}>
                    <Button type="submit" variant="contained">Submit</Button>
                    <Button type="button" onClick={clearForm} variant="contained">Reset</Button>

                </Box>
            </form>
        </div>
    )
}
const boxStyle = {
    width: '80%',
    margin: '40px auto',
    display: 'grid',
    gridTemplateColumns: '33% 33% 33%',
    rowGap: '50px'
}
const boxSubmitStyle = {
    width: '50%',
    margin: '40px auto',
    display: 'grid',
    gridTemplateColumns: '50% 50%',
    columnGap: '40px'

}
export default AddProduct;
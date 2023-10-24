import React,{useState,useReducer, useEffect} from 'react';
import axios from 'axios';
import Box from '@mui/material/Box'
import TextField from '@mui/material/TextField'
import { Button, FormControl, MenuItem, Paper, Select, SelectChangeEvent } from '@mui/material';
import InputLabel from '@mui/material/InputLabel';
import { endPoint, path } from '../../../constant/EndPoint';
let brandDetail:any = [{
    brandId:'asas',
    brandName:'sdsd'
}]

const MenuProps = {
    PaperProps: {
      style: {
        maxHeight: 200,
        width: 250,
      },
    },
  };
  

const AddProduct = () => {
    console.log("ADD PRoduct")
    const dialogdata = {
      message:"success Fully added"
    }
    const getCategory = () =>{
        return ['asas','sasas','sasas']
    }

    const input = {
        productName :'',
        categoryId:'',
        productDescription:'',
        brandId:'',
        productPrice:{

        }
    }
    const [productForm,setProductForm] = useState(input)
    const [brand,setBrands] = useState(brandDetail)

    
    const setProductFormDetail = (e:any)=>{setProductForm(preState=>{return {...preState, [e.target.name]:e.target.value}})}
    const setProductCategory = (e:any)=>{setProductForm(preState=>{  
            return {...preState, productCategory:e.target.value}}
            )}

    
       
    const onSubmit = ()=>{
        console.log(productForm)
    }
    // get All Brand
    useEffect( ()=>{
        console.log("use effect Caleed")
      let url = endPoint.cms + path.cms.getAllBrands;
       let res  = axios.get(url,{})
         .then(res=>{
            console.log("brandList",res)
            setBrands([...res.data]) 
           return res.data
        })
         .catch(err=>{
            console.error(err)
         })
    },[])
    

    return (
        <div>
            <h3>Product Catalog</h3>
            <Box component="form" sx={boxStyle}>
                <div>
                    <TextField  name="productName" onChange={setProductFormDetail} label="Product Name" variant="outlined"></TextField>
                </div>
                <div>
                    <TextField name="productDescription" onChange={setProductFormDetail}     label="Product Description" variant="outlined"></TextField>
                </div>
                <div>
                    <TextField name="categoryId" onChange={setProductFormDetail} label="categoryId" variant="outlined"></TextField>
                </div>
                <div>
                    <FormControl  sx={{ m: 0, width: 200 }}>
                        <InputLabel  id="demo-simple-select-autowidth-label">Brand</InputLabel>

                        <Select 
                            labelId="demo-simple-select-autowidth-label"
                            id="demo-simple-select-autowidth"
                            autoWidth
                            label="Brands"
                            name="brandId"
                            onChange={setProductFormDetail}
                            MenuProps={MenuProps}

                        >
                             {
                                brand.map((data:any,index:number)=>{
                                    return <MenuItem sx={{m:0,width:200}}  key={index}  value={data.brandId}>{data.brandName}</MenuItem>

                                })
                            }
                           
                        </Select>
                    </FormControl>
                </div>
                <div>
                    <FormControl sx={{ m: 0, minWidth: 250 }}>
                        <InputLabel id="demo-simple-select-autowidth-label">Product Category</InputLabel>
                        <Select
                            onChange={setProductCategory}
                            value={productForm.categoryId}
                            labelId="demo-simple-select-autowidth-label"
                            id="demo-simple-select-autowidth"
                            autoWidth
                            label="Product Category"
                        >
                            <MenuItem value="">
                                <em>None</em>
                            </MenuItem>
                           {
                             getCategory().map(data=>{
                                    return  <MenuItem value={22}>{data}</MenuItem>
                                })
                            }
                        </Select>
                    </FormControl>
                </div>
                <div>
                   
                </div>
            </Box>
            <Box component="div">
                <h1>Price Info</h1>

            </Box>
            <Box component="div" sx={boxSubmitStyle}>
               <Button onClick={onSubmit} variant="contained">Submit</Button>
               <Button variant="contained">Reset</Button>


            </Box>
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
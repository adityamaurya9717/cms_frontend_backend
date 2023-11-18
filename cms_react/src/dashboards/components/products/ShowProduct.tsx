import { Box, Button, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TablePagination, TableRow, TextField } from '@mui/material';
import React, { useEffect } from 'react';
import { endPoint, path } from '../../../constant/EndPoint';
import axios from 'axios';
import UpdateProductModal from './UpdateProductModal';
interface GetProductRequest {
    pageNo: number,
    size: number,
    productId: string,
    categoryCode: string,
    brandId: string
}
interface ProductListResponse {
    productId: string,
    productName: string,
    description: string,


}
interface ProductResponse {
    pageNo: number,
    size: number,
    totalCount: number,
    productList: Array<ProductListResponse>
}
let response: ProductResponse = {
    pageNo: 0,
    size: 10,
    totalCount: 0,
    productList: []
}

let columns: Array<string> = ['ProductId', 'ProductName', 'Brand', 'Description', 'Selling Price', 'Tax %', 'Actions']
const ShowProduct = () => {
    // set initial PageNo=0
    const [page, setPage] = React.useState<number>(0);
    // set RowPerPageNo = 10
    const [rowsPerPage, setRowsPerPage] = React.useState<number>(10);
    const [productDetail, setProductDetail] = React.useState<any>({ ...response });
    // for update Product
    const [updateModelData, setUpdateModel] = React.useState<any>({isOpen:false,productData:{}});
 

    const handleChangePage = (event: React.MouseEvent<HTMLButtonElement> | null, newPage: number,) => {
        setPage(newPage);
    };
    const handleChangeRowsPerPage = (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>,) => {
        setRowsPerPage(parseInt(event.target.value));
        setPage(0);
    };
    // call API to Fetch Product List
    useEffect(() => {
        let url = endPoint.cms + path.cms.getProduct;
        let requestPaylaod = {
            pageNo: page,
            size: rowsPerPage,
            categoryCode: '',
            brandId: '',
            productId:''
        };
        (async () => {
            try {
                let res = await axios.post(url, requestPaylaod, {})
                console.log("product response=", res.data)
                let result = res.data as ProductResponse
                setProductDetail({ ...result })
            }
            catch (err) {
                console.error("Error in fetching productList ", err)
            }
        })()

    }, [page, rowsPerPage])

    const openOrCloseModelHandler = (isOpen:boolean,productData:any={})=>{
        if(isOpen==false){
            setUpdateModel(false)
        }
        if(isOpen){
            console.log(productData)
            setUpdateModel({isOpen,productData})
        }
    }

    return (
        <Paper sx={{ width: '100%',overflow: 'hidden' }}>
           {/* for filter */} 
          <Box component="div">
            <div>
                <TextField placeholder='ProductId'> </TextField>
            </div>
            </Box>  
          { updateModelData.isOpen &&   <UpdateProductModal onCloseModelHandler = {openOrCloseModelHandler}  data={updateModelData} /> }
            <TableContainer sx={{ maxHeight: 700 }} style={{ width: '100%', paddingTop: '10px' }} >
                <Table stickyHeader style={{ width: '80%', margin: '0 auto' }} aria-label="sticky table">

                    <TableHead>
                        <TableRow>
                            {
                                columns.map((col, index) => {
                                    return <TableCell style={{ fontSize: '20px' }} variant='head' key={index}> <b>{col}</b></TableCell>
                                })
                            }
                        </TableRow>
                    </TableHead>

                    <TableBody>
                        {
                            productDetail.productList.map((row: any, index: number) => {
                                return (
                                    <TableRow key={index}>
                                        <TableCell>{row.productId}</TableCell>
                                        <TableCell>{row.productName}</TableCell>
                                        <TableCell>{row.brandDetail!==undefined ? row.brandDetail.brandName:'--'}</TableCell>
                                        <TableCell>{row.description}</TableCell>
                                        <TableCell>{row.productPrice!==undefined && row.productPrice!==null ? row.productPrice.sellingPrice:'--'}</TableCell>
                                        <TableCell>{row.productPrice!==undefined && row.productPrice!==null ? row.productPrice.taxPercentage +"%":'--'}</TableCell>
                                        <TableCell><Button onClick={(e)=>openOrCloseModelHandler(true,row)} sx={{height:20}} variant='outlined'>Edit</Button></TableCell>

                                    </TableRow>
                                )
                            })
                         }
                    </TableBody>
                </Table>
            </TableContainer>
            <TablePagination
             style={{margin:'0 auto',width:'50%'}}
             component={Box}
             rowsPerPageOptions={[5, 10, 25, { label: 'All', value: -1 }]}
             colSpan={9}
             count={productDetail.totalCount}
             rowsPerPage={rowsPerPage}
             page={page}
             SelectProps={{
               inputProps: {
                 'aria-label': 'rows per page',
               },
               native: false,
             }}
             onPageChange={handleChangePage}
             onRowsPerPageChange={handleChangeRowsPerPage}
            >
            </TablePagination>
        </Paper>
    )
}
export default ShowProduct;
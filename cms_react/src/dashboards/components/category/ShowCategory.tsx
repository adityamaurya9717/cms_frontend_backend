import React, { useState, useEffect, useCallback } from 'react'
import '../css/getcategory.css'
import { endPoint, path } from '../../../constant/EndPoint'
import { useSearchParams } from 'react-router-dom'
import axios from 'axios'
import { Select } from '@mui/material'


type CategoryListResponse = {
  categoryList: Array<{
    categoryName: string,
    categoryLevel: string
    categoryDescription: string,
    active: boolean,
    parentId: string
  }>

}
type GetCategoryStruct = {
  statusCode: number | null,
  data: {
    pageNo: number,
    size: number,
    totalCount: number,
    categoryList: CategoryListResponse["categoryList"]
  },
  success: boolean,
  message: string,
}
let CategoryResponse: GetCategoryStruct = {
  statusCode: 200,
  success: false,
  message: '',
  data: {
    pageNo: 0,
    size: 10,
    totalCount: 10,
    categoryList: []
  }

}

let GetCategoryPayload = {
  pageNo: 0,
  size: 10,
  categoryLevel: null,
  categoryName: '',
  active: true
}

function ShowCategory() {
  // State for the input value and a timer
  const [categoryNameInput, setCategoryNameInput] = useState('');
  const [debounceTimer, setDebounceTimer] = useState<any>(null);
  const [categoryPayload, setCategoryPayload] = useState({ ...GetCategoryPayload })


  const [categoryResponse, setCategoryResponse] = useState({ ...CategoryResponse });
  const setPaginationHandler = (event: any, pageNo: any) => {
    setCategoryPayload(preState => {
      return { ...preState, pageNo: pageNo }
    })
  }

  // Function to handle changes in the input field
  const handleCategoryNameChange = (e: any) => {
    const value = e.target.value;
    setCategoryNameInput(value);
    // Clear the previous debounce timer
    if (debounceTimer) {
      clearTimeout(debounceTimer);
    }

    // Set a new debounce timer to delay the HTTP request by 500 milliseconds
    const newDebounceTimer = setTimeout(() => {
      setCategoryPayload({ ...categoryPayload, categoryName: value });
    }, 500);

    setDebounceTimer(newDebounceTimer);
  };


  console.log(categoryPayload)

  const requestPayloadHandler = (e: any) => {
    console.log("", e.target.name, e.target.value)
    setCategoryPayload((pre) => {
      let feildName = e.target.name
      if (feildName == 'active') {
        return { ...pre, [e.target.name]: e.target.value == 'true' ? true : false }
      }
      return { ...pre, [e.target.name]: e.target.value }
    })

  }
  async function call() {
    try {
      let url = endPoint.cms + path.cms.getCategory
      let response = await axios.post(url, categoryPayload, {});
      console.log(response)
      let data = response.data;
      console.log("data=>", data)
      if (data.success == false) {
        throw new Error();
      }
      setCategoryResponse({...data})
    }
    catch (err) {
      console.error("error in getting getCategory", err)
    }
  }

  // category page Http REquest CAll
  useEffect(() => {
    call()
  }, [categoryPayload])


  return (
    <div className='get_category_con'>
      {/*  search Category By Filter */}

      <div className='filter_category_container'>
        <div> <input onChange={handleCategoryNameChange} name="categoryName" placeholder='CategoryCode Or CategoryName' ></input> </div>
        <div>
          <select onChange={requestPayloadHandler} name="categoryLevel">
            <option value="NONE">Select Category Level</option>
            <option value="LEVEL_ONE">LEVEL_ONE</option>
            <option value="LEVEL_TWO">LEVEL_TWO</option>
            <option value="LEVEL_THREE">LEVEL_THREE</option>

          </select>
        </div>
        <div>
          <select onChange={requestPayloadHandler} name="active">
            <option value="NONE">Select Category Level</option>
            <option value="true">Active</option>
            <option value="false">InActive</option>

          </select>
        </div>
      </div>

      <table >

        <thead >
          <tr>
            <td>Category Name</td>
            <td>CategoryDescription</td>
            <td>isActive</td>
            <td>Action</td>
          </tr>
        </thead>

        <tbody  >

          {
            categoryResponse.data?.categoryList.map((data, index) => {
              return (
                <tr key={index}>
                  <td>{data.categoryName}</td>
                  <td>{data.categoryDescription}</td>
                  <td>{data.active == true ? "Active" : "Inactive"}</td>
                  <td> <button style={{ float: 'left' }}>Edit</button> &nbsp; &nbsp; <button style={{ float: 'right' }}>Delete</button></td>
                </tr>
              )
            })
          }

        </tbody>
        <tfoot>
        </tfoot>
      </table>
      <div >
        {/*
          pagination bar
           */
           }
        <div className="pagination_container" >
          <div className="show_user-pagination_bar_con">
            {
              new Array(Math.ceil(categoryResponse.data?.totalCount / categoryPayload.size)).fill(1).map((data, index) => {
                return (<button
                  onClick={(e) => { setPaginationHandler(e, index) }}
                  key={index}
                  style={{ backgroundColor :index==categoryPayload.pageNo?'lightgreen':''}}
                  className="page_box">
                  {index + 1}
                </button>);
              })
            }

          </div>
        </div>

      </div>



    </div>
  )
}


export default ShowCategory
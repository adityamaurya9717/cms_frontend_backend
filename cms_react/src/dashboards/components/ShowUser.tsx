import React, { useRef, useCallback, useEffect, useState } from 'react';
import { endPoint, path } from '../../constant/EndPoint'
import { useNavigate, useLocation,useSearchParams,useParams  } from 'react-router-dom'
import axios from "axios";

import './css/showuser.css'
import ShowUserRequest from '../../model/ShowUserRequest';

let response = {
  pageNo:1,
  size:1,
  totalCount:1,
  users:[]
}
let requestPayload:ShowUserRequest = {
  name:'',
  email:'',
  pageNo:1,
  size:5
}
const ShowUser = () => {

  const location = useLocation();
  const [searchParams, setSearchParams] = useSearchParams()
  const queryParams = new URLSearchParams(location.search);
  // Access individual query parameters
  const pageNo = queryParams.get('pageNo')==null? 1 : queryParams.get('pageNo') ;  

  const [users, setUsers] = useState(response);
  const [emailtype,setEmail] = useState('');
  const [pagination,setPagination] = useState(pageNo);


  const headers: Array<string> = ['FirstName', 'LastName', 'email', 'phone', 'delete']
  let url = endPoint.cms + path.cms.getuser

  const setEmailHandler = (event:any)=>{
    setEmail(preState=>{
      return event.target.value;
    })

  }
  const setPaginationHandler = (event:any,pageNo:any)=>{
    setPagination(pageNo)
  }

  
  const _loadShowUser = async (pagenumber:any) => {
    try {
      console.log(url)
      let response = await axios.post(url,{...requestPayload,pageNo:pagenumber},{})
                      .then(response => {
                        console.log(response)
                        return response.data;
                      })
      console.log("responsedata=",response);
      setUsers((preState: any)=>{
       return response
      })
    }
    catch (ex) {
      console.error(ex)
    }

  }
  useEffect(() => {
    console.log("call effect")
    setSearchParams({ 'pageNo' : String(pagination==null?1:pagination) });

    _loadShowUser(pagination)
  }, [emailtype,pagination])

  // delete customer

  const deleteHandler = async (event: any, id: any) => {
       let url = endPoint.cms + path.cms.deleteuser + "/?id="+id
       try{
        let result = await axios.delete(url,{})
        let res = result
        _loadShowUser(pageNo);
       }
       catch (ex){
          console.error(ex)
       }
  }

  return (
    <div className="show_user">
      <div>
        <input type="text" placeholder='email' onChange={setEmailHandler}></input>
        <input type="text" placeholder='name' onChange={setEmailHandler}></input>
      </div>
      <table>
        <tr>
          {
            headers.map((header,index) => <th key={index}>{header}</th>)
          }
        </tr>
        <tbody>
        {
          users.users.map((row:any, index:any) => {
            return <tr key={index}>
              <td>{row.firstName}</td>
              <td>{row.lastName}</td>
              <td>{row.email}</td>
              <td>{row.phone}</td>
              <td><button id={row.id} onClick={(e) => { deleteHandler(e, row.id) }}>delete</button></td>
            </tr>

          })



        }
        </tbody>

      </table>
      {/*
          pagination bar
           */}
      <div className="pagination_container" >
        <div className="show_user-pagination_bar_con">
          {
            new Array(Math.ceil(users.totalCount/5)).fill(1).map((data,index) => {
              return( <button 
                onClick={(e)=>{setPaginationHandler(e,index+1)}}
                 key={index}
                className="page_box">
                  {index+1}
                  </button>);
            })
          }

        </div>



      </div>
    </div>
  )
}

export default ShowUser;
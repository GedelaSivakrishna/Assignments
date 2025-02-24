import { CircularProgress } from '@mui/material'
import React from 'react'

const Loader = () => {
  return (
    <div className='h-screen w-full flex justify-center items-center'>
          <CircularProgress />
    </div>
  )
}

export default Loader
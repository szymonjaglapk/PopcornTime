import React from 'react'
import './ContentBox.css'

const ContentBox = props => {
  return (
    <div className="content_main">
        {props.children}
    </div>
  )
}

export default ContentBox
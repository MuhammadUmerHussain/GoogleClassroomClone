import axios from 'axios';

const link = 'http://localhost:8086';


export const  viewAllPost= async (class_id) => {
    console.log(`${link}/viewAllPost`)
    return await axios.get(`${link}/viewAllPost`, class_id);
    
}

export const editPost = async (obj) => {
    return await axios.put(`${link}/editPost`, obj);
}

export const deletePost = async (obj) => {
    return await axios.delete(`${link}/deletePost`,obj);
}

export const createPost = async (obj) => {
    return await axios.put(`${link}/createPost`, obj)
}
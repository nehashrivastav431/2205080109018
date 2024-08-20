import axios from "axios";
import React, { useEffect, useState } from "react";

const ProductsPage = () => {
  const [products, setProducts] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await axios.get(
          "http://localhost:3000/api/products"
        );
        setProducts(response.data);
        setIsLoading(false);
      } catch (error) {
        setError(error);
        setIsLoading(false);
      }
    };
    fetchProducts();
  }, []);

  if (isLoading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  return (
    <div>
      <h1>All Products</h1>
      <ul>
        {products.map((product) => (
          <li key={product.id}>
            <h2>{product.name}</h2>
            <p>
              <strong>Company:</strong> {product.company}
            </p>
            <p>
              <strong>Category:</strong> {product.category}
            </p>
            <p>
              <strong>Price:</strong> {product.price}
            </p>
            <p>
              <strong>Rating:</strong> {product.rating}
            </p>
            <p>
              <strong>Discount:</strong> {product.discount}
            </p>
            <p>
              <strong>Availability:</strong> {product.availability}
            </p>
          </li>
        ))}
      </ul>
    </div>
  );
};


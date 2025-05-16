import { Link } from "react-router-dom";

import "./CategoryLink.css";

const CategoryLink = ({ category }) => {
  return (
    <li>
      <Link
        to={`/category/${category.category_id}`}
        className="content_categories_blocks_movie"
      >
        {category.category_name}
      </Link>
    </li>
  );
};

export default CategoryLink;

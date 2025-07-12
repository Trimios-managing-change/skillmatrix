import React, { useEffect, useState } from "react";
import "../../pagescss/StudentHeader.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBell, faUser } from "@fortawesome/free-solid-svg-icons";
import axios from "axios";

import BASE_URL from "../../config";

const StudentHeader = () => {
  const token  = localStorage.getItem("token");
  const [username, setUsername] = useState("");
  console.log("Token from localStorage:", token); // Debugging line

  useEffect(() => {
    const fetchUsername = async () => {
      try {
        const response = await axios.get(`${BASE_URL}/api/student/home`, {
          headers: { Authorization: `Bearer ${token}` }
        });
        setUsername(response.data.fullName);
         // Corrected field name
        console.log("Username fetched successfully", response.data.fullName);
      } catch (err) {
        console.error("Failed to fetch username", err);
      }
    };

    fetchUsername();
  }, [token]);

  return (
    <header className="student-header">
      <div className="logo">Skill Matrix</div>
      <div className="header-right">
        <FontAwesomeIcon icon={faBell} className="icon" />
        <div className="user-box">
          <FontAwesomeIcon icon={faUser} className="icon" />
          <span>{username}</span>
        </div>
      </div>
    </header>
  );
};

export default StudentHeader;

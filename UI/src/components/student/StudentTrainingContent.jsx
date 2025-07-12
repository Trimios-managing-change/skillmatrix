// src/components/student/StudentTrainingContent.jsx
import React, { useEffect, useState } from "react";
import axios from "axios";
import { useAuth } from "../../context/AuthContext";
import BASE_URL from "../../config";
import "../../pagescss/StudentTrainingContent.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlayCircle } from "@fortawesome/free-solid-svg-icons";

const StudentTrainingContent = () => {
  const  token  = localStorage.getItem("token"); 
  const [trainingData, setTrainingData] = useState(null);

  useEffect(() => {
    const fetchTrainingData = async () => {
      try {
        const res = await axios.get(
          `${BASE_URL}/api/student/unverified?roleName=DevOps`,
          {
            headers: { Authorization: `Bearer ${token}` },
          }
        );
        setTrainingData(res.data.data);
      } catch (err) {
        console.error("Error fetching training data:", err);
      }
    };

    fetchTrainingData();
  }, [token]);

  if (!trainingData)
    return (
      <div className="training-container">
        <h2>Training</h2>
        <p>Loading...</p>
      </div>
    );

  const sortedSkills = [...trainingData.allSkills].sort((a, b) => {
    if (a.verificationStatus === "verifying" && b.verificationStatus !== "verifying") return -1;
    if (a.verificationStatus !== "verifying" && b.verificationStatus === "verifying") return 1;
    return 0;
  });

  return (
    <div className="training-container">
      <h2>{trainingData.roleName} Training</h2>
      <div className="training-cards">
        {sortedSkills.map((skill, idx) => (
          <div
            key={idx}
            className={`training-card ${
              skill.verificationStatus === "verifying" ? "highlighted" : ""
            }`}
          >
            <h3>{skill.skillName}</h3>
            <p>Status: <strong>{skill.verificationStatus}</strong></p>
            <a href={skill.courseLink} target="_blank" rel="noopener noreferrer">
              <FontAwesomeIcon icon={faPlayCircle} style={{ marginRight: "8px" }} />
              View Course
            </a>
          </div>
        ))}
      </div>
    </div>
  );
};

export default StudentTrainingContent;

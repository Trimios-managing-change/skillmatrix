import React, { useEffect, useState } from "react";
import axios from "axios";
import { useAuth } from "../../context/AuthContext";
import BASE_URL from "../../config";
import "../../pagescss/SkillAssessmentContent.css";

const SkillAssessmentContent = () => {
  const token = localStorage.getItem("token");
  const [roleData, setRoleData] = useState(null);
  const [expanded, setExpanded] = useState(false);
  const [selectedLevels, setSelectedLevels] = useState({});

  useEffect(() => {
    const fetchSkills = async () => {
      try {
        const res = await axios.get(`${BASE_URL}/api/student/DevOps`, {
          headers: { Authorization: `Bearer ${token}` }
        });
        
        console.log("API Response:", res); // Log the full API response
  
        // Check if the response status is successful
        if (res.data.status && res.data.data && res.data.data.data) {
          // Successfully fetched skills data
          setRoleData(res.data.data.data); // Set the correct roleData from the API response
          console.log("Role Data:", res.data.data.data);
        } else {
          // If status is false, handle the error response
          console.error("Error: ", res.data.message || "Unknown error");
          alert("Error fetching skills: " + (res.data.message || "Unknown error"));
        }
      } catch (err) {
        console.error("Error fetching skills:", err);
        alert("Failed to fetch skills. Please try again later.");
      }
    };
  
    fetchSkills();
  }, [token]);
  

  const handleLevelChange = (skillName, level) => {
    setSelectedLevels(prev => ({
      ...prev,
      [skillName]: level
    }));
  };

  const handleSubmit = async () => {
    const payload = {
      roleName: roleData?.roleName,
      skillLevels: selectedLevels
    };

    try {
      const res = await axios.post(`${BASE_URL}/api/student/submit-assessment`, payload, {
        headers: { Authorization: `Bearer ${token}` }
      });

      if (res.status === 200 || res.data.status === true) {
        alert("Skill assessment submitted successfully!");
        setRoleData(res.data.data); // Update the view to reflect new statuses
        setSelectedLevels({});
      }
    } catch (err) {
      alert("Failed to submit skill assessment.");
      console.error(err);
    }
  };

  if (!roleData) {
    return (
      <div className="assessment-container">
        <h1>Skill Assessment</h1>
        <p>Failed to load data or no skills found.</p>
      </div>
    );
  }

  return (
    <>
      <h1>Skill Assessment</h1>

      <div className="assessment-container">
        <div className="role-card" onClick={() => setExpanded(!expanded)}>
          <h3>{roleData.roleName}</h3>
          <p>{expanded ? "Hide Skills ▲" : "Show Skills ▼"}</p>
        </div>

        {expanded && roleData.skills?.length > 0 && (
          <div className="skills-list">
            {roleData.skills.map(skill => (
              <div key={skill.skillName} className="skill-item">
                <span>{skill.skillName}</span>
                <select
                  value={selectedLevels[skill.skillName] || ""}
                  onChange={(e) => handleLevelChange(skill.skillName, e.target.value)}
                >
                  <option value="">Select Level</option>
                  <option value="Intermediate">Intermediate</option>
                  <option value="Advanced">Advanced</option>
                  <option value="Expert">Expert</option>
                </select>
                <span className={`status-tag ${skill.status.toLowerCase()}`}>
                  {skill.status}
                </span>
              </div>
            ))}
            <button className="submit-btn" onClick={handleSubmit}>Submit Assessment</button>
          </div>
        )}
      </div>
    </>
  );
};

export default SkillAssessmentContent;

import React, { useEffect, useState } from "react";
import axios from "axios";
import { useAuth } from "../../context/AuthContext";
import BASE_URL from "../../config"; // Make sure this points to the correct URL
import "../../pagescss/StudentProfileContent.css";

const StudentProfileContent = () => {
  const token = localStorage.getItem("token");
  const [isEditing, setIsEditing] = useState(false);

  const [profile, setProfile] = useState({
    name: "",
    course: "",
    sscInstitute: "",
    sscMarks: "",
    interInstitute: "",
    interMarks: "",
    graduationInstitute: "",
    internshipExperiences: [""],
    description: "",
    email: "",
    phone: "",
  });

  // Fetch profile on component mount
  useEffect(() => {
    const fetchProfile = async () => {
      try {
        console.log("Fetching profile...");
        const res = await axios.get(`${BASE_URL}/api/student/get`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        console.log("Fetched profile:", res.data);
        setProfile(res.data); // Assume the backend sends profile details in this format
      } catch (err) {
        console.error("Error fetching profile:", err.response || err.message);
      }
    };

    fetchProfile();
  }, [token]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setProfile((prev) => ({ ...prev, [name]: value }));
  };

  const handleExperienceChange = (index, value) => {
    const updated = [...profile.internshipExperiences];
    updated[index] = value;
    setProfile((prev) => ({ ...prev, internshipExperiences: updated }));
  };

  const addExperience = () => {
    setProfile((prev) => ({
      ...prev,
      internshipExperiences: [...prev.internshipExperiences, ""],
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Prepare the data based on the expected API payload
    const profileData = {
      name: profile.name,
      course: profile.course,
      sscInstitute: profile.sscInstitute,
      sscMarks: parseFloat(profile.sscMarks), // Ensure this is a number
      interInstitute: profile.interInstitute,
      interMarks: parseFloat(profile.interMarks), // Ensure this is a number
      graduationInstitute: profile.graduationInstitute,
      internshipExperiences: profile.internshipExperiences,
      description: profile.description,
      email: profile.email,
      phone: profile.phone,
    };

    console.log("Submitting profile data:", profileData);

    try {
      const res = await axios.post(`http://localhost:8011/api/student/save`, profileData, {
        headers: { Authorization: `Bearer ${token}` },
      });
      console.log("Profile save response:", res.data);

      if (res.data.message === "Profile saved successfully.") {
        alert("Profile updated successfully.");
        setIsEditing(false);
      } else {
        alert("Failed to save profile: " + res.data.message);
      }
    } catch (err) {
      console.error("Failed to update profile:", err.response || err.message);
      alert("Failed to update profile.");
    }
  };

  return (
    <div className="profile-container">
      <h2>{profile.name}</h2>

      {isEditing ? (
        <form onSubmit={handleSubmit} className="profile-form">
          <label>
            Name:
            <input name="name" value={profile.name} onChange={handleChange} required />
          </label>

          <label>
            Course:
            <input name="course" value={profile.course} onChange={handleChange} required />
          </label>

          <label>
            SSC Institute:
            <input name="sscInstitute" value={profile.sscInstitute} onChange={handleChange} required />
          </label>

          <label>
            SSC Marks:
            <input name="sscMarks" type="number" step="0.1" value={profile.sscMarks} onChange={handleChange} required />
          </label>

          <label>
            Inter Institute:
            <input name="interInstitute" value={profile.interInstitute} onChange={handleChange} required />
          </label>

          <label>
            Inter Marks:
            <input name="interMarks" type="number" step="0.1" value={profile.interMarks} onChange={handleChange} required />
          </label>

          <label>
            Graduation Institute:
            <input name="graduationInstitute" value={profile.graduationInstitute} onChange={handleChange} required />
          </label>

          <label>
            Internship Experiences:
            {profile.internshipExperiences.map((exp, idx) => (
              <input
                key={idx}
                value={exp}
                onChange={(e) => handleExperienceChange(idx, e.target.value)}
                placeholder={`Internship #${idx + 1}`}
              />
            ))}
            <button type="button" className="edit-button" onClick={addExperience}>Add Experience</button>
          </label>

          <label>
            Profile Summary:
            <textarea name="description" value={profile.description} onChange={handleChange} required />
          </label>

          <label>
            Email:
            <input name="email" type="email" value={profile.email} onChange={handleChange} required />
          </label>

          <label>
            Phone:
            <input name="phone" value={profile.phone} onChange={handleChange} required />
          </label>

          <button type="submit" className="edit-button">Save</button>
          <button type="button" className="edit-button" onClick={() => setIsEditing(false)}>Cancel</button>
        </form>
      ) : (
        <div className="profile-details">
          <p><strong>Name:</strong> {profile.name}</p>
          <p><strong>Course:</strong> {profile.course}</p>
          <p><strong>SSC Institute:</strong> {profile.sscInstitute} ({profile.sscMarks})</p>
          <p><strong>Inter Institute:</strong> {profile.interInstitute} ({profile.interMarks})</p>
          <p><strong>Graduation Institute:</strong> {profile.graduationInstitute}</p>
          <p><strong>Internships:</strong></p>
          <ul>
            {profile.internshipExperiences.map((exp, idx) => (
              <li key={idx}>{exp}</li>
            ))}
          </ul>
          <p><strong>Summary:</strong> {profile.description}</p>
          <p><strong>Email:</strong> {profile.email}</p>
          <p><strong>Phone:</strong> {profile.phone}</p>
          <button className="edit-button" onClick={() => setIsEditing(true)}>Edit Profile</button>
        </div>
      )}
    </div>
  );
};

export default StudentProfileContent;

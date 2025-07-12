// src/components/student/StudentProfileOverlay.jsx
import React, { useEffect, useState } from "react";
import axios from "axios";
import BASE_URL from "../../config";
import { useAuth } from "../../context/AuthContext";
import "../../pagescss/StudentProfileOverlay.css";

const StudentProfileOverlay = ({ onClose }) => {
  const  token  = localStorage.getItem("token");  // Use token from context or localStorage
  const [name, setName] = useState("");
  const [form, setForm] = useState({
    name: "",
    course: "",
    sscInstitute: "",
    sscMarks: "",
    interInstitute: "",
    interMarks: "",
    graduationInstitute: "",
    internshipExperiences: "",
    description: "",
    email: "",
    phone: "",
  });

  useEffect(() => {
    const fetchProfileName = async () => {
      try {
        const res = await axios.get(`${BASE_URL}/api/student/get-user-profile`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        setName(res.data.name || "");
        setForm((prev) => ({ ...prev, name: res.data.name || "" }));
      } catch (err) {
        console.error("Error fetching profile name:", err);
      }
    };

    fetchProfileName();
  }, [token]);

  const handleChange = (e) => {
    setForm((prev) => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const payload = {
      ...form,
      internshipExperiences: form.internshipExperiences
        .split(",")
        .map((item) => item.trim()),
    };

    try {
      await axios.post(`${BASE_URL}/api/student/profile`, payload, {
        headers: { Authorization: `Bearer ${token}` },
      });
      onClose(); // close overlay on success
    } catch (err) {
      alert("Failed to submit profile: " + err.response?.data?.message);
    }
  };

  return (
    <div className="overlay">
      <div className="overlay-content">
        <h2>Hello, {name}</h2>
        <p>Please complete your profile</p>
        <form onSubmit={handleSubmit}>
          <input name="name" placeholder="Full Name" value={form.name} onChange={handleChange} required />
          <input name="course" placeholder="Course" value={form.course} onChange={handleChange} required />
          <input name="sscInstitute" placeholder="SSC Institute" value={form.sscInstitute} onChange={handleChange} required />
          <input name="sscMarks" placeholder="SSC Marks (e.g., 8.0)" value={form.sscMarks} onChange={handleChange} required />
          <input name="interInstitute" placeholder="Intermediate Institute" value={form.interInstitute} onChange={handleChange} required />
          <input name="interMarks" placeholder="Intermediate Marks (e.g., 8.5)" value={form.interMarks} onChange={handleChange} required />
          <input name="graduationInstitute" placeholder="Graduation Institute" value={form.graduationInstitute} onChange={handleChange} required />
          <input name="internshipExperiences" placeholder="Internship Experiences (comma-separated)" value={form.internshipExperiences} onChange={handleChange} required />
          <textarea name="description" placeholder="Profile Description" value={form.description} onChange={handleChange} required />
          <input name="email" placeholder="Email" value={form.email} onChange={handleChange} required />
          <input name="phone" placeholder="Phone Number" value={form.phone} onChange={handleChange} required />
          <button type="submit">Submit Profile</button>
        </form>
      </div>
    </div>
  );
};

export default StudentProfileOverlay;

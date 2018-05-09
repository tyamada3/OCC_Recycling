package edu.orangecoastcollege.cs272.capstone.model;

public class Customer {

	private int mId;
	private String mName;
	private String mUser;
	private String mRole;
	private String mEmail;

    public Customer(int mId, String mName, String mUser, String mRole, String mEmail)
    {
        super();
        this.mId = mId;
        this.mName = mName;
        this.mUser = mUser;
        this.mRole = mRole;
        this.mEmail = mEmail;
    }

    public int getmId()
    {
        return mId;
    }

    public void setmId(int mId)
    {
        this.mId = mId;
    }

    public String getmName()
    {
        return mName;
    }

    public void setmName(String mName)
    {
        this.mName = mName;
    }

    public String getmUser()
    {
        return mUser;
    }

    public void setmUser(String mUser)
    {
        this.mUser = mUser;
    }

    public String getmRole()
    {
        return mRole;
    }

    public void setmRole(String mRole)
    {
        this.mRole = mRole;
    }

    public String getmEmail()
    {
        return mEmail;
    }

    public void setmEmail(String mEmail)
    {
        this.mEmail = mEmail;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mEmail == null) ? 0 : mEmail.hashCode());
        result = prime * result + mId;
        result = prime * result + ((mName == null) ? 0 : mName.hashCode());
        result = prime * result + ((mRole == null) ? 0 : mRole.hashCode());
        result = prime * result + ((mUser == null) ? 0 : mUser.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Customer other = (Customer) obj;
        if (mEmail == null)
        {
            if (other.mEmail != null) return false;
        }
        else if (!mEmail.equals(other.mEmail)) return false;
        if (mId != other.mId) return false;
        if (mName == null)
        {
            if (other.mName != null) return false;
        }
        else if (!mName.equals(other.mName)) return false;
        if (mRole == null)
        {
            if (other.mRole != null) return false;
        }
        else if (!mRole.equals(other.mRole)) return false;
        if (mUser == null)
        {
            if (other.mUser != null) return false;
        }
        else if (!mUser.equals(other.mUser)) return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "Customer [mId=" + mId + ", mName=" + mName + ", mUser=" + mUser + ", mRole=" + mRole + ", mEmail="
                + mEmail + "]";
    }



}

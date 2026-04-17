import mysql.connector
import pandas as pd
import datetime

def get_connection():
    return mysql.connector.connect(
        host='localhost',
        user='root',
        password='sushila1#',
        database='newdb'
    )

def generate_reports():
    conn = get_connection()
    df_detail = pd.read_sql("SELECT * FROM customer_detail", conn)
    
    current_year = datetime.datetime.now().year
    df_detail['age'] = current_year - pd.to_datetime(df_detail['customer_date_of_birth']).dt.year
    
    def get_age_group(age):
        if age < 18: return 'Under 18'
        elif 18 <= age <= 35: return '18-35'
        elif 36 <= age <= 60: return '36-60'
        return 'Over 60'
        
    df_detail['age_group'] = df_detail['age'].apply(get_age_group)
    lang_age_report = df_detail.groupby(['customer_preferred_language', 'age_group']).size().reset_index(name='count')
    lang_age_report.to_csv('language_age_group_report.csv', index=False)
    print("Generated: language_age_group_report.csv")

    gender_counts = df_detail['customer_gender'].value_counts()
    gender_ratio = pd.DataFrame({
        'Gender': gender_counts.index,
        'Count': gender_counts.values
    })
    gender_ratio.to_csv('gender_ratio_report.csv', index=False)
    print("Generated: gender_ratio_report.csv")

    country_lang_report = df_detail.groupby(['customer_country_of_origin', 'customer_preferred_language']).size().reset_index(name='count')
    country_lang_report.to_csv('country_language_distribution.csv', index=False)
    print("Generated: country_language_distribution.csv")

    conn.close()

if __name__ == "__main__":
    generate_reports()

import mysql.connector
import pandas as pd
import matplotlib.pyplot as plt
import datetime

def get_connection():
    return mysql.connector.connect(
        host='localhost',
        user='root',
        password='sushila1#',
        database='newdb'
    )

def generate_visualisations():
    conn = get_connection()
    
    df_addr = pd.read_sql("SELECT * FROM customer_address", conn)
    plt.figure(figsize=(10, 6))
    if not df_addr.empty:
        df_addr['customer_address_type'].value_counts().plot(kind='bar', color='skyblue')
        plt.title('Most Common Address Types')
        plt.xlabel('Address Type')
        plt.ylabel('Count')
        plt.tight_layout()
        plt.savefig('address_types_bar_chart.png')
        print("Generated: address_types_bar_chart.png")
    plt.close()

    df_contact = pd.read_sql("SELECT * FROM customer_contact_information", conn)
    plt.figure(figsize=(10, 6))
    if not df_contact.empty:
        current_date = pd.Timestamp(datetime.date.today())
        df_contact['end_date'] = pd.to_datetime(df_contact['end_date'])
        df_contact['status'] = df_contact['end_date'].apply(lambda d: 'Expired' if d < current_date else 'Active')
        
        df_contact['status'].value_counts().plot(kind='bar', color=['lightgreen', 'salmon'])
        plt.title('Active vs Expired Contact Information Records')
        plt.xlabel('Status')
        plt.ylabel('Count')
        plt.tight_layout()
        plt.savefig('active_vs_expired_records.png')
        print("Generated: active_vs_expired_records.png")
    plt.close()

    df_detail = pd.read_sql("SELECT * FROM customer_detail", conn)
    plt.figure(figsize=(10, 6))
    if not df_detail.empty:
        df_detail['customer_preferred_language'].value_counts().plot(kind='barh', color='coral')
        plt.title('Most Preferred Languages by Customers')
        plt.xlabel('Count')
        plt.ylabel('Language')
        plt.tight_layout()
        plt.savefig('preferred_languages_horizontal_bar.png')
        print("Generated: preferred_languages_horizontal_bar.png")
    plt.close()

    conn.close()

if __name__ == "__main__":
    generate_visualisations()

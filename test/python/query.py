from gql import Client, gql
from gql.transport.aiohttp import AIOHTTPTransport

if __name__ == '__main__':
    transport = AIOHTTPTransport(url="http://localhost:8080/graphql")

    client = Client(transport=transport, fetch_schema_from_transport=True)

    query = gql(
        """
        query getAllPeople {
        getAllPeople {
            firstName
            lastName
        }
        }
        """
    )

    result = client.execute(query)
    print(result)

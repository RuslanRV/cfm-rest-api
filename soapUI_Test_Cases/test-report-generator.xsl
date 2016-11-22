<?xml version="1.0" encoding="ISO-8859-1"?>
<?mso-application progid="Excel.Sheet"?>
<xsl:stylesheet version="1.0" xmlns:html="http://www.w3.org/TR/REC-html40"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="urn:schemas-microsoft-com:office:spreadsheet"
	xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel"
	xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet" xmlns:con="http://eviware.com/soapui/config"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">

	<xsl:template match="/">

		<Workbook>
			<Styles>
				<Style ss:ID="Default" ss:Name="Normal">
					<Alignment ss:Vertical="Center" ss:Horizontal="Left"
						ss:WrapText="1" />
					<Borders>
						<Border ss:Position="Left" ss:Color="#000000" ss:LineStyle="Continuous" ss:Weight="1"></Border>
						<Border ss:Position="Top" ss:Color="#000000" ss:LineStyle="Continuous" ss:Weight="1"></Border>
						<Border ss:Position="Right" ss:Color="#000000" ss:LineStyle="Continuous" ss:Weight="1"></Border>
						<Border ss:Position="Bottom" ss:Color="#000000" ss:LineStyle="Continuous" ss:Weight="1"></Border>
					</Borders>
				</Style>
				<Style ss:ID="testSuite" ss:Name="testSuite">
					<Alignment ss:Vertical="Center" ss:Horizontal="Left"
						ss:WrapText="1" />
					<Interior ss:Color='#EEECE1' ss:Pattern='Solid' />
				</Style>
				<Style ss:ID="testCase" ss:Name="testCase">
					<Alignment ss:Vertical="Center" ss:Horizontal="Left"
						ss:WrapText="1" />
					<Interior ss:Color='#C5D9F1' ss:Pattern='Solid' />
				</Style>
				<Style ss:ID="testStep" ss:Name="testStep">
					<Alignment ss:Vertical="Center" ss:Horizontal="Left"
						ss:WrapText="1" />
					<Interior ss:Color='#9BBB59' ss:Pattern='Solid' />
				</Style>
				<Style ss:ID="assertion" ss:Name="assertion">
					<Alignment ss:Vertical="Center" ss:Horizontal="Left"
						ss:WrapText="1" />
					<Interior ss:Color='#F79646' ss:Pattern='Solid' />
				</Style>
				<Style ss:ID="xpath" ss:Name="xpath">
					<Alignment ss:Vertical="Center" ss:Horizontal="Left"
						ss:WrapText="1" />
					<Interior ss:Color='#FFF19B' ss:Pattern='Solid' />
				</Style>
				<Style ss:ID="expected" ss:Name="expected">
					<Alignment ss:Vertical="Center" ss:Horizontal="Left"
						ss:WrapText="1" />
					<Interior ss:Color='#CCC0DA' ss:Pattern='Solid' />
				</Style>
			</Styles>

			<Worksheet ss:Name="test">
				<Table>
					<Column ss:Width="200" />
					<Column ss:Width="180" />
					<Column ss:Width="180" />
					<Column ss:Width="180" />
					<Column ss:Width="230" />
					<Column ss:Width="380" />
					<xsl:for-each select="//con:testSuite">

						<Row ss:Height="30">
							<Cell ss:MergeAcross="5" ss:StyleID="testSuite">
								<Data ss:Type="String">
									<xsl:value-of select="concat(@name, ' (', con:description, ')')" />
								</Data>
							</Cell>
						</Row>
						<xsl:for-each select="con:testCase">
							<Row ss:Height="30">
								<Cell ss:Index="2" ss:MergeAcross="4" ss:StyleID="testCase">
									<Data ss:Type="String">
										<xsl:value-of select="concat(@name, ' (', con:description, ')')" />
									</Data>
								</Cell>
							</Row>
							<xsl:for-each select="con:testStep">
								<xsl:if test="@type != 'transfer'">
									<Row>
										<Cell ss:Index="3" ss:MergeAcross="3" ss:StyleID="testStep">
											<Data ss:Type="String">
												<xsl:value-of select="@name" />
											</Data>
										</Cell>
									</Row>
									
									<xsl:for-each select="con:config/con:restRequest/con:assertion">
										<Row>
											<xsl:variable name="assertionName">
												<xsl:choose>
													<xsl:when test="@name">
														<xsl:value-of select="@name" />
													</xsl:when>
													<xsl:otherwise>
														<xsl:text> Validate the Response is a Validate SOAP message </xsl:text>
													</xsl:otherwise>
												</xsl:choose>
											</xsl:variable>
											<Cell ss:Index="4" ss:StyleID="assertion">
												<Data ss:Type="String">
													<xsl:value-of select="$assertionName" />
												</Data>
											</Cell>
											<xsl:if test="@type = 'XPath Match'">
												<Cell ss:StyleID="xpath">
													<Data ss:Type="String">
														<xsl:value-of select="con:configuration/path" />
													</Data>
												</Cell>
												<Cell ss:StyleID="expected">
													<Data ss:Type="String">
														<xsl:value-of select="con:configuration/content" />
													</Data>
												</Cell>
											</xsl:if>
										</Row>
									</xsl:for-each>
								</xsl:if>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>


				</Table>
			</Worksheet>


		</Workbook>

	</xsl:template>


</xsl:stylesheet>
